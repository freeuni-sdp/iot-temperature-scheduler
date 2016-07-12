import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaneki on 7/12/16.
 */
public class FakeList {

    private static FakeList instance;

    private List<House> houseList;

    private FakeList(List<House> houses) {
        houseList = houses;
    }

    public static FakeList instance() {
        if (instance == null)
            instance = new FakeList(new ArrayList<House>());
        return instance;
    }

    public List<House> getHouseList() {
        return houseList;
    }

    public void addHouse(House house) {
        houseList.add(house);
    }

    public void clear() {
        houseList.clear();
    }
}
