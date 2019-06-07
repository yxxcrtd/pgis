package com.igoosd.service;

import com.igoosd.domain.FlowInfoData;
import com.igoosd.util.CommonService;

import java.util.List;

public interface FlowInfoDataService extends CommonService<FlowInfoData,Long> {



    /**
     * 根据条件查询 list
     * @param flowInfoData
     * @return
     */
    List<FlowInfoData> findAll(FlowInfoData flowInfoData);

}
