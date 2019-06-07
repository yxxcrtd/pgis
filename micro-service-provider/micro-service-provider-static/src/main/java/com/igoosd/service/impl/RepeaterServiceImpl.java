package com.igoosd.service.impl;

import com.igoosd.domain.Repeater;
import com.igoosd.repository.RepeaterRepository;
import com.igoosd.repository.dao.RepeaterDao;
import com.igoosd.service.RepeaterService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RepeaterServiceImpl extends AbsCommonService<Repeater, Long> implements RepeaterService {

    @Autowired
    private RepeaterRepository repeaterRepository;
    @Autowired
    private RepeaterDao repeaterDao;


    @Override
    public Page<Repeater> findPageList(Repeater repeater, Pageable pageable) {
        return repeaterDao.findPage(repeater,pageable);
    }

    @Override
    protected JpaRepository<Repeater, Long> getRepository() {
        return repeaterRepository;
    }

}
