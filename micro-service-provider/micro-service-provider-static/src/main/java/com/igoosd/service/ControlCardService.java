package com.igoosd.service;

import com.igoosd.domain.ControlCard;
import com.igoosd.util.CommonService;

import java.util.List;

public interface ControlCardService extends CommonService<ControlCard, Long> {


    /**
     * 根据主键列表 查询codes
     * @param ids
     * @return
     */
     List<String> findCodesByIds(List<Long> ids);

}
