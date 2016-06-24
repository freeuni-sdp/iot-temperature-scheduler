package ge.edu.freeuni.sdp.service.scheduler.temperature.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vato on 6/12/16.
 */
public class Schedule {

    private List<TemperatureEntry> entryList;

    public Schedule() {
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

    public Object getSchedule(int dateFrom, int dateTo){
        return null;
    }

    @Override
    public String toString() {
        String returnVal = "";
        for (TemperatureEntry entry:entryList){
            returnVal += "" + entry.getDateFrom() + " " + entry.getDateTo() + "||";
        }
        return returnVal;
    }

    public static void main(String[] agrs){


    }
}
