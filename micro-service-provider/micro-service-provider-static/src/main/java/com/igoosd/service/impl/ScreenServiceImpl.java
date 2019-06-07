package com.igoosd.service.impl;

import com.igoosd.domain.Screen;
import com.igoosd.repository.ScreenRepository;
import com.igoosd.repository.dao.ScreenDao;
import com.igoosd.service.ScreenService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScreenServiceImpl extends AbsCommonService<Screen, Long> implements ScreenService {

    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ScreenDao screenDao;

    @Override
    protected JpaRepository<Screen, Long> getRepository() {
        return screenRepository;
    }

    @Override
    public Page<Screen> findPageList(Screen screen, Pageable pageable) {
        return screenDao.findPage(screen,pageable);
    }

    @Override
    public List<Screen> findByControlCardIdOrderByOrderNumAsc(Long controlCardId) {
        return screenRepository.findByControlCardIdOrderByOrderNumAsc(controlCardId);
    }

}
