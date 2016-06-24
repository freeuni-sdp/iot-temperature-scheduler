package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vato on 6/24/16.
 */
public class House {

    private String houseIndex;
    private List<Floor> floorList;

    public House(String houseIndex) {
        this.houseIndex = houseIndex;
        this.floorList = new ArrayList<>();
    }

    public String getHouseIndex() {
        return houseIndex;
    }

    public void addFloor(int index) {
        Floor floor = new Floor(index);
        floor.setSchedule(new Schedule());
        this.floorList.add(floor);
    }

    public JSONArray getSchedule(int floorId, long dateFrom, long dateTo) {
        for (Floor floor : this.floorList) {
            if (floor.getFloorIndex() == floorId) {
                return floor.getScheduleRange(dateFrom, dateTo);
            }
        }
        return null;
    }
}
