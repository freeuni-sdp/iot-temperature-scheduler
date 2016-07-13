import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.TemperatureEntry;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by kaneki on 7/12/16.
 */
public class ScheduleManagerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(FakeScheduleManager.class);
    }

    @Test
    public void get_empty_list() {
        FakeList.instance().clear();
        FakeList.instance().addHouse(new House("1"));
        Response resp = target("/houses/1/floors/1/schedule").request().get();
    }

    @Test
    public void get_test_2(){
        System.out.println("Testing get 2");
        FakeList.instance().clear();
        House house = new House("1");
        house.addFloor("1");
        TemperatureEntry entry = new TemperatureEntry(1, 2, 12);
        house.addSchedule("1", entry);
        FakeList.instance().addHouse(house);

        Response resp = target("/houses/1/floors/1/schedule")
                .queryParam("start", 1)
                .queryParam("end", 2)
                .request().get();

        assertEquals(Response.Status.OK.getStatusCode(), resp.getStatus());
        JSONObject jsonObject = (JSONObject) resp.getEntity();
        System.out.println(jsonObject.toString());
    }

    @Test
    public void post_404_test(){
        FakeList.instance().clear();
        TemperatureEntry entry = new TemperatureEntry(1, 2, 12);
        Response resp = target("/houses/1/floors/1/").request().post(Entity.entity(entry, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resp.getStatus());

    }

    @Test
    public void post_200_test(){
        FakeList.instance().clear();
        TemperatureEntry entry = new TemperatureEntry(1, 2, 12);
        House house = new House("1");
        house.addFloor("1");
        FakeList.instance().addHouse(house);
        Response resp = target("/houses/1/floors/1/").request().post(Entity.entity(entry, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.OK.getStatusCode(), resp.getStatus());
        assertEquals(1, FakeList.instance().getHouseList().size());

        JSONArray jsonArray = FakeList.instance().getHouseList().get(0).getSchedule("1", 0, 2);
        assertNotNull(jsonArray);

        assertEquals(1, jsonArray.length());
        assertEquals(1, jsonArray.getJSONObject(0).getLong("dateFrom"));
        assertEquals(2, jsonArray.getJSONObject(0).getLong("dateTo"));
        assertEquals(12, jsonArray.getJSONObject(0).getLong("temperature"));
    }
}
