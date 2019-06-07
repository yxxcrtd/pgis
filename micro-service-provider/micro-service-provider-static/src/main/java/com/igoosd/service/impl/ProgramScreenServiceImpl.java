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

import com.igoosd.domain.ProgramScreen;
import com.igoosd.domain.Screen;
import com.igoosd.enumeration.ScreenTypeEnum;
import com.igoosd.repository.ProgramScreenRepository;
import com.igoosd.service.ProgramScreenService;
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
public class ProgramScreenServiceImpl extends AbsCommonService<ProgramScreen, Long> implements ProgramScreenService {
	
	@Autowired
    private ProgramScreenRepository programScreenRepository;
	@Autowired
    private ScreenService screenService;
	
  
	@Override
    protected JpaRepository<ProgramScreen, Long> getRepository() {
        return programScreenRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindScreen(Long programId, List<Long> ids) {

        List<Screen> list = screenService.findAll(ids);
        Assert.isTrue(!CollectionUtils.isEmpty(list),"无效的屏幕id集合");
        for (Screen screen : list){
            Assert.isTrue(ScreenTypeEnum.MEDIA.getValue() == screen.getType(),"不合法的屏幕类型绑定");
        }
        ProgramScreen ps = new ProgramScreen();
        ps.setProgramId(programId);
        this.delete(ps);
        for (Long id :ids){
            ProgramScreen programScreen = new ProgramScreen();
            programScreen.setProgramId(programId);
            programScreen.setScreenId(id);
            programScreen.setCreateTime(new Date());
            programScreenRepository.saveAndFlush(programScreen);
        }
    }
}


