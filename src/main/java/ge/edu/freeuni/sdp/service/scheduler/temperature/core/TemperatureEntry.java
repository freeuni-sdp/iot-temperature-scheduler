package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vato on 6/12/16.
 */
@XmlRootElement
public class TemperatureEntry {

    @XmlElement
    private long dateFrom;
    @XmlElement
    private long dateTo;
    @XmlElement
    private int temperature;

    public TemperatureEntry() {
    }

    public TemperatureEntry(long dateFrom, long dateTo, int temperature) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.temperature = temperature;
    }

    public long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public long getDateTo() {
        return dateTo;
    }

    public void setDateTo(long dateTo) {
        this.dateTo = dateTo;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
