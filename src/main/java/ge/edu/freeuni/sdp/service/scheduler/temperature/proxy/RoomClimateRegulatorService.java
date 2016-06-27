package ge.edu.freeuni.sdp.service.scheduler.temperature.proxy;

import ge.edu.freeuni.sdp.service.scheduler.temperature.core.Notification;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.ServiceState;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * Created by vato on 6/24/16.
 */
public class RoomClimateRegulatorService {

    private static final String ROOM_CLIMATE_REGULATOR_API = "https://iot-room-climate-regulator.herokuapp.com";
    private static final String ROOM_CLIMATE_REGULATOR_MOCK =
            "https://private-028973-iotroomclimateregulator.apiary-mock.com/webapi";

    private Client client;
    private String url;

    /**
     * Creates new RoomClimateRegulatorService according to API provided at
     * http://docs.iotroomclimateregulator.apiary.io/
     * @param state ServiceState
     */
    public RoomClimateRegulatorService(ServiceState state) {
        this.url = ROOM_CLIMATE_REGULATOR_API;
        if (state == ServiceState.MOCK) {
            this.url = ROOM_CLIMATE_REGULATOR_MOCK;
        }
        ClientConfig config = new ClientConfig().register(JacksonFeature.class);
        this.client = ClientBuilder.newClient(config);
    }

    /**
     * Creates and sends request to url of API (Mock or REAL)
     * @param houseId int describing house number
     * @param floorId int describing floor number
     * @param entity Entity containing JSON to send
     */
    public void createRequest(int houseId, int floorId, Entity entity) {
        String requestUrl = String.format("%s/houses/%s/floors/%s/task", this.url, houseId, floorId);
        System.out.println(requestUrl);
        client.target(requestUrl).request().put(entity);
    }

    public void createRequest(Notification requestInfo) {
        String requestUrl = String.format("%s/houses/%s/floors/%s/task",
                this.url,
                requestInfo.getHouseIndex(),
                requestInfo.getFloorIndex());
        client.target(requestUrl)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(requestInfo.getSchedule().toString()));
//        client.target(requestUrl).request().put(requestInfo.getSchedule().toString());
    }

}
