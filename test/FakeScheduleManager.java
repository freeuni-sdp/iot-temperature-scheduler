import ge.edu.freeuni.sdp.service.scheduler.temperature.ScheduleManager;
import ge.edu.freeuni.sdp.service.scheduler.temperature.core.House;

import java.util.List;

/**
 * Created by kaneki on 7/12/16.
 */
public class FakeScheduleManager extends ScheduleManager{


    public List<House> getHouseList() {
        return FakeList.instance().getHouseList();
    }
}
