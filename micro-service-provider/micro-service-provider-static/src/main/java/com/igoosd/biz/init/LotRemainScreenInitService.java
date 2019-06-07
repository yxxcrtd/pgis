package com.igoosd.biz.init;

import com.igoosd.biz.ScreenInitProperties;
import com.igoosd.domain.ControlCard;
import com.igoosd.domain.Parking;
import com.igoosd.domain.ParkingScreen;
import com.igoosd.domain.Screen;
import com.igoosd.enumeration.ScreenTypeEnum;
import com.igoosd.service.ParkingScreenService;
import com.igoosd.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.igoosd.util.Constants.INIT_PAGE_PARAM_SPLIT;

/**
 * 车位数屏显示操作
 */
@Service
public class LotRemainScreenInitService extends AbsScreenInitService {

    @Autowired
    private ParkingScreenService parkingScreenService;
    @Autowired
    private ScreenInitProperties screenInitProperties;
    @Autowired
    private ParkingService parkingService;


    @Override
    public ScreenTypeEnum getScreenType() {
        return ScreenTypeEnum.LOT_COUNT;
    }

    @Override
    protected String getInitUrl(List<Screen> screenList, ControlCard controlCard) {
        //查找排序情况
        StringBuilder carNumStr = new StringBuilder();
        StringBuilder idsStr = new StringBuilder();
        //screenList 已排序
        for (Screen screen : screenList) {
            ParkingScreen temp = new ParkingScreen();
            temp.setScreenId(screen.getId());
            ParkingScreen pss = parkingScreenService.getByScreenId(screen.getId());
            //Assert.notNull(pss, "屏幕-停车场关系未维护，请确保数据准确性.屏幕ID:" + screen.getId());
            //未绑定 默认设置 剩余停车位为0
            int lotRemainCount= 0;
            if(null != pss){
                Parking parking = parkingService.getEntityById(pss.getParkingId());
                lotRemainCount = parking.getLotRemainCount();
            }
            carNumStr.append(lotRemainCount).append(INIT_PAGE_PARAM_SPLIT);
            idsStr.append(screen.getId()).append(INIT_PAGE_PARAM_SPLIT);
        }
        carNumStr.setLength(carNumStr.length() - 1);
        idsStr.setLength(idsStr.length() - 1);

        return screenInitProperties.getDomain() + "content/lotPage?ids=" + idsStr.toString() + "&carNumbers=" + carNumStr+"&warnNum="+ screenInitProperties.getWarnNum();
    }



}
