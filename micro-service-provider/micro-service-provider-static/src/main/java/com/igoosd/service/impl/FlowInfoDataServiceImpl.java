package com.igoosd.service.impl;

import com.igoosd.domain.FlowInfoData;
import com.igoosd.repository.FlowInfoDataRepository;
import com.igoosd.service.FlowInfoDataService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class FlowInfoDataServiceImpl extends AbsCommonService<FlowInfoData,Long> implements FlowInfoDataService {

    @Autowired
    private FlowInfoDataRepository flowInfoDataRepository;

    @Override
    protected JpaRepository<FlowInfoData, Long> getRepository() {
        return this.flowInfoDataRepository;
    }

    @Override
    public List<FlowInfoData> findAll(FlowInfoData flowInfoData) {
        return flowInfoDataRepository.findAll(Example.of(flowInfoData));
    }

}
