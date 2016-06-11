package ge.edu.freeuni.sdp.service.scheduler.temperature;

import ge.edu.freeuni.sdp.service.scheduler.temperature.core.Schedule;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.TemperatureEntry;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by vato on 6/12/16.
 */
@Path("/")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class ScheduleManager {

    @Path("/houses/{house_id}/floors/{floor_id}/?{start_time}&{end_time}")
    @GET
    public Schedule get(@PathParam("house_id") int houseId, @PathParam("floor_id") int floorId,
                        @PathParam("start_time") int dateFrom, @PathParam("end_time") int dateTo ) {
        return new Schedule(new Date(dateFrom), new Date(dateTo));
    }

    @Consumes( {MediaType.APPLICATION_JSON} )
    @Path("/houses/{house_id}/floors/{floor_id}/")
    @POST
    public Response setSchedule(TemperatureEntry entry) {
        return Response.ok().build();
    }

}
