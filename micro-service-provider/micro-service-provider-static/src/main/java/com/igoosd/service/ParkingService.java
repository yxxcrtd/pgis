package com.igoosd.service;

import com.igoosd.domain.Parking;
import com.igoosd.util.CommonService;

import java.util.List;

public interface ParkingService extends CommonService<Parking, Long> {

    /**
     * 更新指定停车场剩余车位数
     * @param exitNum
     * @param enterNum
     * @param id
     * @return
     */
    int changeLotRemain(int exitNum,int enterNum,Long id);

    /**
     * 根据诱导牌ID 查询关联停车场列表
     * @param inducePlateId
     * @return
     */
    List<Parking> getParkingListByInducePlateId(Long inducePlateId);
}
