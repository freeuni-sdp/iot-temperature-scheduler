package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by vato on 6/12/16.
 */
@XmlRootElement
public class TemperatureEntry {

    @XmlElement
    private Date dateFrom;
    @XmlElement
    private Date dateTo;
    @XmlElement
    private int temperature;

    public TemperatureEntry() {
    }

    public TemperatureEntry(Date dateFrom, Date dateTo, int temperature) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.temperature = temperature;
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

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
