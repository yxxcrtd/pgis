/**
 * @单位名称：安徽谷声科技有限公司
 *            IGOOSD Technology Co.,Ltd.
 * 		      Copyright (c) 2017 All Rights Reserved.
 * @系统名称：路泊收费系统
 * @工程名称：后台服务
 * @文件名称: 
 * @类路径: 
 */

package com.igoosd.service;



/**
 * @see
 *
 * @date	2017-10-30 15:10:49 中国标准时间
 * @version	V0.0.1
 * @desc    TODO
 */

import com.igoosd.domain.ParkingScreen;
import com.igoosd.util.CommonService;

import java.util.List;

public interface ParkingScreenService extends CommonService<ParkingScreen, Long>{


   void bindScreen(Long parkingId,List<Long> screenIds);

   ParkingScreen getByScreenId(Long screenId);

}


