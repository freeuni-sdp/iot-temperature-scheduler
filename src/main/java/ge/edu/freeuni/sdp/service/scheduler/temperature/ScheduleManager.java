package ge.edu.freeuni.sdp.service.scheduler.temperature;

import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.TemperatureEntry;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vato on 6/12/16.
 */
@Path("/houses/{house_id}/floors/{floor_id}")
public class ScheduleManager {

    @Produces( {MediaType.APPLICATION_JSON})
    @Path("/schedule")
    @GET
    public JSONArray get(@PathParam("house_id") String houseId, @PathParam("floor_id") String floorId,
                         @QueryParam("start") long dateFrom, @QueryParam("end") long dateTo) {
        JSONArray answer = new JSONArray();
        for (House house: TemperatureScheduler.getHouseList()) {
            if (house.getHouseIndex().equals(houseId)) {
                answer = house.getSchedule(floorId, dateFrom, dateTo);
            }
        }
        return answer;
    }

    @POST
    @Path("/")
    @Consumes( {MediaType.APPLICATION_JSON})
    public Response post(@PathParam("house_id") String houseId, @PathParam("floor_id") String floorId,
                         TemperatureEntry entry) {
        for (House house: TemperatureScheduler.getHouseList()) {
            if (house.getHouseIndex().equals(houseId)) {
                house.addSchedule(floorId, entry);
            }
        }
        return Response.ok().build();
    }

}
