package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vato on 6/12/16.
 */
public class Schedule {

    private Date dateFrom;
    private Date dateTo;
    private List<TemperatureEntry> entryList;

    public Schedule(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.entryList = new ArrayList<>();
    }

    public void addEntry(TemperatureEntry entry) {
        this.entryList.add(entry);
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public List<TemperatureEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<TemperatureEntry> entryList) {
        this.entryList = entryList;
    }

}
