package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import org.json.JSONArray;

/**
 * Created by vato on 6/24/16.
 */
public class Floor {

    private int floorIndex;
    private Schedule schedule;

    public Floor(int floorIndex) {
        this.floorIndex = floorIndex;
        this.schedule = null;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getFloorIndex() {
        return floorIndex;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public JSONArray getScheduleRange(long dateFrom, long dateTo) {
        return this.schedule.getSchedule(dateFrom, dateTo);
    }
}
