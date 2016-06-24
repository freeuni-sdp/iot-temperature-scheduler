package ge.edu.freeuni.sdp.service.scheduler.temperature;

import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.Schedule;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.ServiceState;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.TemperatureEntry;
import ge.edu.freeuni.sdp.service.scheduler.temperature.proxy.HouseRegistryService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vato on 6/12/16.
 */
@Path("/")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class ScheduleManager {

    private List<House> houseList;

    private void checkHouses(List<String> ids) {
        for (String houseId : ids) {
            boolean contains = false;
            for (House house : houseList) {
                if (house.getHouseIndex().equals(houseId)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                houseList.add(new House(houseId));
            }
        }
        List<House> removeList = new ArrayList<>();
        for (House house: houseList) {
            if (!ids.contains(house.getHouseIndex())) {
                removeList.add(house);
            }
        }
        for (House house: removeList) {
            houseList.remove(house);
        }
    }

    public ScheduleManager() {
        final HouseRegistryService houseRegistryService = new HouseRegistryService(ServiceState.REAL);
        houseList = new ArrayList<>();
        Thread thr = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        List<String> ids = houseRegistryService.refreshAll();
                        checkHouses(ids);
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thr.start();
    }

    @Path("/houses/{house_id}/floors/{floor_id}/?{start_time}&{end_time}")
    @GET
    public Response get(@PathParam("house_id") String houseId, @PathParam("floor_id") int floorId,
                         @PathParam("start_time") long dateFrom, @PathParam("end_time") long dateTo) {
        JSONArray answer = new JSONArray();
        for (House house: this.houseList) {
            if (house.getHouseIndex().equals(houseId)) {
                answer = house.getSchedule(floorId, dateFrom, dateTo);
            }
        }
        return Response.ok().entity(answer).build();
    }

    @Consumes( {MediaType.APPLICATION_JSON} )
    @Path("/houses/{house_id}/floors/{floor_id}/")
    @POST
    public Response setSchedule(TemperatureEntry entry) {
        return Response.ok().build();
    }

}
