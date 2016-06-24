package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vato on 6/12/16.
 */
public class Schedule {

    private List<TemperatureEntry> entryList;
    private int defaultTemp;

    public Schedule() {
        this.defaultTemp = 18;
        this.entryList = new ArrayList<>();
    }

    public void addEntry(TemperatureEntry entry) {
        for (int k = 0; k < entryList.size(); k++){
            k = intersect(entry, k);
        }
        int insertIndex = 0;
        for (int k = entryList.size() - 1; k >= 0; k--){
            if (entry.getDateFrom() >= entryList.get(k).getDateTo()){
                insertIndex = k + 1;
                break;
            }
        }
        entryList.add(insertIndex, entry);
    }

    private int intersect(TemperatureEntry entry, int index){
        TemperatureEntry temp = entryList.get(index);
        if (temp.getDateFrom() <= entry.getDateFrom() && entry.getDateTo() <= temp.getDateTo()){
            split(entry, index);
            System.out.println(1);
            return index;
        }

        if (temp.getDateFrom() >= entry.getDateFrom() && entry.getDateTo() >= temp.getDateTo()){
            entryList.remove(index);
            System.out.println(2);
            return index - 1;
        }

        if (temp.getDateFrom() <= entry.getDateFrom() && temp.getDateTo() >= entry.getDateFrom()){
            temp.setDateTo(entry.getDateFrom());
            System.out.println(3);
            return index;
        }

        if (temp.getDateFrom() <= entry.getDateTo() && temp.getDateTo() >= entry.getDateTo()){
            temp.setDateFrom(entry.getDateTo());
            System.out.println(4);
            return index;
        }
        return index;
    }

    private void split(TemperatureEntry entry, int index){
        TemperatureEntry temp = entryList.get(index);
        TemperatureEntry leftSide = new TemperatureEntry(temp.getDateFrom(), entry.getDateFrom(), temp.getTemperature());
        TemperatureEntry rightSide = new TemperatureEntry(entry.getDateTo(), temp.getDateTo(), temp.getTemperature());
        entryList.remove(index);
        addEntry(leftSide);
        addEntry(rightSide);
    }

    public List<TemperatureEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<TemperatureEntry> entryList) {
        this.entryList = entryList;
    }

    public JSONArray getSchedule(long dateFrom, long dateTo){
        JSONArray requestedList = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("dateFrom", new Long(dateFrom));
        obj.put("dateTo", new Long(dateTo));
        obj.put("temperature", new Long(defaultTemp));
        requestedList.put(obj.toString());
        for (TemperatureEntry anEntryList : entryList) {
            if (contains(anEntryList, dateFrom, dateTo)) {
                JSONObject temp = new JSONObject();
                temp.put("dateFrom", anEntryList.getDateFrom());
                temp.put("dateTo", anEntryList.getDateTo());
                temp.put("temperature", anEntryList.getTemperature());
                requestedList.put(temp);
            }
        }

        return requestedList;
    }

    private boolean contains(TemperatureEntry temp, long dateFrom, long dateTo){

        return  (temp.getDateFrom() >= dateFrom && temp.getDateFrom() <= dateTo) ||
                (temp.getDateTo() >= dateFrom && temp.getDateTo() <= dateTo);
    }

    @Override
    public String toString() {
        String returnVal = "";
        for (TemperatureEntry entry:entryList){
            returnVal += "" + entry.getDateFrom() + " " + entry.getDateTo() + "||";
        }
        return returnVal;
    }

}
