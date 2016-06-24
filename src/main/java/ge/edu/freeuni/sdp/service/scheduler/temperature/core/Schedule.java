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

        int subListStartIndex = 0, subListEndIndex = 0;
        for (int k = 0; k < entryList.size(); k++) {
            if (entryList.get(k).getDateTo() >= entry.getDateFrom() && subListStartIndex == 0) {
                entryList.get(k).setDateTo(entry.getDateFrom());
                subListStartIndex = k;
            }
            if (entryList.get(k).getDateTo() >= entry.getDateTo())  {
                entryList.get(k).setDateFrom(entry.getDateTo());
                subListEndIndex = k;
                break;
            }
        }

        List removeList = entryList.subList(subListStartIndex, subListEndIndex);
        entryList.add(subListStartIndex, entry);
        entryList.removeAll(removeList);

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
            returnVal += "" + entry.getDateFrom() + " " + entry.getDateTo() + " " + entry.getTemperature() + "||";
        }
        return returnVal;
    }

//    public static void main(String[] agrs){
//
//        Schedule schedule = new Schedule();
//        schedule.addEntry(new TemperatureEntry(1, 10, 5));
//        System.out.println(schedule);
//
//
//    }
}
