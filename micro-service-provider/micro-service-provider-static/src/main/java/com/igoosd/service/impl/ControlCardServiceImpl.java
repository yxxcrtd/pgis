package com.igoosd.service.impl;

import com.igoosd.domain.ControlCard;
import com.igoosd.repository.ControlCardRepository;
import com.igoosd.repository.dao.ControlCardDao;
import com.igoosd.service.ControlCardService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ControlCardServiceImpl extends AbsCommonService<ControlCard, Long> implements ControlCardService {

    @Autowired
    private ControlCardRepository controlCardRepository;
    @Autowired
    private ControlCardDao controlCardDao;


    @Override
    public Page<ControlCard> findPageList(ControlCard controlCard, Pageable pageable) {
        return controlCardDao.findPage(controlCard,pageable);
    }

    @Override
    protected JpaRepository<ControlCard, Long> getRepository() {
        return this.controlCardRepository;
    }

    @Override
    public List<String> findCodesByIds(List<Long> ids) {
        return controlCardRepository.findCodesByIds(ids);
    }
}
