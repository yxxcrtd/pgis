/**
 * @单位名称：安徽谷声科技有限公司 IGOOSD Technology Co.,Ltd.
 * Copyright (c) 2017 All Rights Reserved.
 * @系统名称：路泊收费系统
 * @工程名称：后台服务
 * @文件名称:
 * @类路径:
 */

package com.igoosd.repository;

import com.igoosd.domain.ParkingScreen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @see
 * @date 2017-10-30 15:10:49 中国标准时间
 * @version V0.0.1
 * @desc TODO
 */
public interface ParkingScreenRepository extends JpaRepository<ParkingScreen, Long>, JpaSpecificationExecutor<ParkingScreen> {

    /**
     * 根据屏幕Id查找ParkingScreen 信息
     * @param screenId
     * @return
     */
    ParkingScreen getByScreenId(Long screenId);

    /**
     * 检查 制定屏幕中是否绑定了 其他停车场
     * @param parkingId
     * @param screenIds
     */
    @Query(nativeQuery = true, value = "select count(1)>0 from t_parking_screen t" +
            " where t.parking_id != :parkingId and  t.screen_id in (:ids) ")
    boolean hasBindParkingForScreenIds(@Param("parkingId") Long parkingId, @Param("ids") List<Long> screenIds);
}




