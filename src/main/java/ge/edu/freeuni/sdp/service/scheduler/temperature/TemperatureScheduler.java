package ge.edu.freeuni.sdp.service.scheduler.temperature;

import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.ServiceState;
import ge.edu.freeuni.sdp.service.scheduler.temperature.proxy.HouseRegistryService;
import ge.edu.freeuni.sdp.service.scheduler.temperature.proxy.RoomClimateRegulatorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vato on 6/25/16.
 */
public class TemperatureScheduler {

    private static List<House> houseList;

    private void checkHouses(List<String> ids) {
        for (String houseId : ids) {
            boolean contains = false;
            for (House house : houseList) {
                if (house.getHouseIndex().equals(houseId)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                houseList.add(new House(houseId));
            }
        }
        List<House> removeList = new ArrayList<>();
        for (House house: houseList) {
            if (!ids.contains(house.getHouseIndex())) {
                removeList.add(house);
            }
        }
        for (House house: removeList) {
            houseList.remove(house);
        }
        System.out.println(houseList.size());
    }

    public void start() {
        final HouseRegistryService houseRegistryService = new HouseRegistryService(ServiceState.REAL);
        houseList = new ArrayList<>();
        Thread thr = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        List<String> ids = houseRegistryService.refreshAll();
                        checkHouses(ids);
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thr.start();
    }

    public static List<House> getHouseList() {
        return houseList;
    }
}
