package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import java.util.List;

/**
 * Created by vato on 6/24/16.
 */
public class Floor {

    private String floorIndex;
    private Schedule schedule;

    public Floor(String floorIndex) {
        this.floorIndex = floorIndex;
        this.schedule = null;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getFloorIndex() {
        return floorIndex;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public List<TemperatureEntry> getScheduleRange(long dateFrom, long dateTo) {
        return this.schedule.getSchedule(dateFrom, dateTo);
    }

    public void setScheduleRange(TemperatureEntry entry) {
        this.schedule.addEntry(entry);
    }

    public Notification getNotification(){
        /**
         * Returns info for Room climate regulator
         **/
        return new Notification(floorIndex, schedule.getCurSchedule());
    }
}
