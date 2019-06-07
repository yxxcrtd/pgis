/**
 * @单位名称：安徽谷声科技有限公司
 *            IGOOSD Technology Co.,Ltd.
 * 		      Copyright (c) 2017 All Rights Reserved.
 * @系统名称：路泊收费系统
 * @工程名称：后台服务
 * @文件名称: 
 * @类路径: 
 */

package com.igoosd.service.impl;


/**
 * @see
 *
 * @date	2017-10-30 15:10:49 中国标准时间
 * @version	V0.0.1
 * @desc    TODO
 */
import com.igoosd.domain.ParkingScreen;
import com.igoosd.domain.Screen;
import com.igoosd.enumeration.ScreenTypeEnum;
import com.igoosd.exception.StaticException;
import com.igoosd.repository.ParkingScreenRepository;
import com.igoosd.service.ParkingScreenService;
import com.igoosd.service.ScreenService;
import com.igoosd.util.AbsCommonService;
import com.igoosd.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ParkingScreenServiceImpl extends AbsCommonService<ParkingScreen, Long> implements ParkingScreenService {
	
	@Autowired
    private ParkingScreenRepository parkingScreenRepository;
	@Autowired
    private ScreenService screenService;
	
  
	@Override
    protected JpaRepository<ParkingScreen, Long> getRepository() {
        return parkingScreenRepository;
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindScreen(Long parkingId, List<Long> screenIds) {

        List<Screen> list = screenService.findAll(screenIds);
        Assert.isTrue(!CollectionUtils.isEmpty(list),"无效的屏幕id集合");
        //屏幕类型校验
        for (Screen screen : list){
            Assert.isTrue(ScreenTypeEnum.LOT_COUNT.getValue() == screen.getType(),"不合法的屏幕类型绑定");
        }
        //查看屏幕是否绑定了其他停车场
        boolean flag = parkingScreenRepository.hasBindParkingForScreenIds(parkingId, screenIds);
	    if(flag){
	        throw new StaticException("所选屏幕列表中存在相关屏幕被其他停车场绑定情况");
        }
        ParkingScreen temp = new ParkingScreen();
	    temp.setParkingId(parkingId);
	    this.delete(temp);
        for (Long screenId : screenIds){
            ParkingScreen ps =  new ParkingScreen();
            ps.setParkingId(parkingId);
            ps.setScreenId(screenId);
            ps.setCreateTime(new Date());
            parkingScreenRepository.saveAndFlush(ps);
        }
    }

    @Override
    public ParkingScreen getByScreenId(Long screenId) {
        return parkingScreenRepository.getByScreenId(screenId);
    }
}


