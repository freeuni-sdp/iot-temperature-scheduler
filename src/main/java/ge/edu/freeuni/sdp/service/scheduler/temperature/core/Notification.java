package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import org.json.JSONObject;

/**
 * Created by kaneki on 6/27/16.
 */
public class Notification {

    private String floorIndex;
    private String houseIndex;
    private JSONObject schedule;

    public JSONObject getSchedule() {
        return schedule;
    }

    public String getFloorIndex() {
        return floorIndex;
    }

    public String getHouseIndex() {
        return houseIndex;
    }

    public void setHouseIndex(String houseIndex) {
        this.houseIndex = houseIndex;
    }

    public Notification(String floorIndex, JSONObject schedule){
        this.floorIndex = floorIndex;
        this.schedule = schedule;
    }

}
