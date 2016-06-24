package ge.edu.freeuni.sdp.service.scheduler.temperature;

import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.Schedule;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.ServiceState;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.TemperatureEntry;
import ge.edu.freeuni.sdp.service.scheduler.temperature.proxy.HouseRegistryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by vato on 6/12/16.
 */
@Path("/")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class ScheduleManager {

    public ScheduleManager() {
        final HouseRegistryService houseRegistryService = new HouseRegistryService(ServiceState.REAL);
        Thread thr = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        houseRegistryService.refreshAll();
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
    public Schedule get(@PathParam("house_id") int houseId, @PathParam("floor_id") int floorId,
                        @PathParam("start_time") long dateFrom, @PathParam("end_time") long dateTo ) {
        return new Schedule();
    }

    @Consumes( {MediaType.APPLICATION_JSON} )
    @Path("/houses/{house_id}/floors/{floor_id}/")
    @POST
    public Response setSchedule(TemperatureEntry entry) {
        return Response.ok().build();
    }

}
