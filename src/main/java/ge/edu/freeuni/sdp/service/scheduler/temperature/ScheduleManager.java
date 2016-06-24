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
@Path("/houses/{house_id}/floors/{floor_id}")
@Produces( { MediaType.APPLICATION_JSON})
public class ScheduleManager {

    @Path("/{start}&{end}")
    @GET
    public JSONArray get(@PathParam("house_id") String houseId, @PathParam("floor_id") int floorId,
                         @PathParam("start") long dateFrom, @PathParam("end") long dateTo) {
        JSONArray answer = new JSONArray();
        for (House house: TemperatureScheduler.getHouseList()) {
            if (house.getHouseIndex().equals(houseId)) {
                answer = house.getSchedule(floorId, dateFrom, dateTo);
            }
        }
        return answer;
    }

    @Path("/")
    @POST
    public Response post(@PathParam("house_id") String houseId, @PathParam("floor_id") int floorId) {
        return Response.ok().build();
    }

}
