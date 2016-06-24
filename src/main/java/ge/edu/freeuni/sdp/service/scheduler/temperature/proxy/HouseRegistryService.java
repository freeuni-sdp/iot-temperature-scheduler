package ge.edu.freeuni.sdp.service.scheduler.temperature.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.ServiceState;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vato on 6/24/16.
 */
public class HouseRegistryService {

    private static final String HOUSE_REGISTRY_API = "https://iot-house-registry.herokuapp.com/houses";
    private static final String HOUSE_REGISTRY_MOCK =
            "http://private-7901ae-iothouseregistry.apiary-mock.com/houses";

    private String url;
    private List<House> houseList;
    private Client client;

    public HouseRegistryService(ServiceState state) {
        this.url = HOUSE_REGISTRY_API;
        if (state == ServiceState.MOCK) {
            this.url = HOUSE_REGISTRY_MOCK;
        }
        ClientConfig config = new ClientConfig().register(JacksonFeature.class);
        this.client = ClientBuilder.newClient(config);
        this.houseList = new ArrayList<>();
    }

    public void refreshAll() {
        Response response = this.client.target(url).request().get();
        JSONArray arr = new JSONArray(response.readEntity(String.class));
        List<String> houseIds = new ArrayList<>();
        for (Object item : arr) {
            JSONObject obj = (JSONObject) item;
            String key = obj.getJSONObject("RowKey").getString("_");
            houseIds.add(key);
        }
        for (String houseId : houseIds) {
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
        System.out.println(houseList.size());
    }

}
