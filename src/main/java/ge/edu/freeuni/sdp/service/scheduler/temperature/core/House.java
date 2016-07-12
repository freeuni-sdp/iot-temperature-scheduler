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
        for (int i=1; i<4; i++) {
            String id = "floor" + i;
            addFloor(id);
        }
    }

    public String getHouseIndex() {
        return houseIndex;
    }

    public void addFloor(String index) {
        Floor floor = new Floor(index);
        floor.setSchedule(new Schedule());
        this.floorList.add(floor);
    }

    public JSONArray getSchedule(String floorId, long dateFrom, long dateTo) {
        for (Floor floor : this.floorList) {
            if (floor.getFloorIndex().equals(floorId)) {
                return floor.getScheduleRange(dateFrom, dateTo);
            }
        }
        return new JSONArray();
    }

    public void addSchedule(String floorId, TemperatureEntry entry) {
        for (Floor floor : this.floorList) {
            if (floor.getFloorIndex().equals(floorId)) {
                floor.setScheduleRange(entry);
            }
        }
    }

    public List<Notification> getNotifications(){
        List<Notification> schedules = new ArrayList<>();
        Notification temp;
        for (Floor floor: floorList) {
            temp = floor.getNotification();
            temp.setHouseIndex(houseIndex);
            schedules.add(temp);
        }
        return schedules;
    }
}
