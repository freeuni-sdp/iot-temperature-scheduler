package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import javax.ws.rs.client.Entity;
import java.util.List;

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

    public Entity getScheduleRange(long dateFrom, long dateTo) {
        return (Entity) this.schedule.getSchedule(dateFrom, dateTo);
    }
}
