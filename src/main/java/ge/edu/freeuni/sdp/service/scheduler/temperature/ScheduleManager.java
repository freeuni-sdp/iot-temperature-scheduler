package ge.edu.freeuni.sdp.service.scheduler.temperature;

import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.TemperatureEntry;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by vato on 6/12/16.
 */
@Path("/houses/{house_id}/floors/{floor_id}")
public class ScheduleManager {

    protected List<House> getHouseList() {
        return TemperatureScheduler.getHouseList();
    }

    @Produces( {MediaType.APPLICATION_JSON})
    @Path("/schedule")
    @GET
    public JSONArray get(@PathParam("house_id") String houseId, @PathParam("floor_id") String floorId,
                         @QueryParam("start") long dateFrom, @QueryParam("end") long dateTo) {

        JSONArray answer = new JSONArray();
        boolean validHouseID = false;
        for (House house: getHouseList()) {
            if (house.getHouseIndex().equals(houseId)) {
                validHouseID = true;
                answer = house.getSchedule(floorId, dateFrom, dateTo);
            }
        }

        if (validHouseID)
            return answer;
        throw new NotFoundException();
    }

    @POST
    @Consumes( {MediaType.APPLICATION_JSON})
    public Response post(@PathParam("house_id") String houseId, @PathParam("floor_id") String floorId,
                         TemperatureEntry entry) {
        boolean validHouseID = false;
        for (House house: getHouseList()) {
            if (house.getHouseIndex().equals(houseId)) {
                validHouseID = true;
                house.addSchedule(floorId, entry);
            }
        }
        if (validHouseID)
            return Response.ok().build();

        throw new NotFoundException();
    }

}
