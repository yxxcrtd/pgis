package com.igoosd.service;

import com.igoosd.domain.Screen;
import com.igoosd.util.CommonService;

import java.util.List;

/**
 * led屏幕接口
 */
public interface ScreenService extends CommonService<Screen, Long> {


    /**
     * 根据控制卡id查找屏幕 列表  orderNum Asc 排序
     * @param controlCardId
     * @return
     */
    List<Screen> findByControlCardIdOrderByOrderNumAsc(Long controlCardId);

}
