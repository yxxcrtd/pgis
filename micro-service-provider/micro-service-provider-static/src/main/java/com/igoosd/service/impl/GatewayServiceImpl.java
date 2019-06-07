package com.igoosd.service.impl;

import com.igoosd.domain.Gateway;
import com.igoosd.repository.GatewayRepository;
import com.igoosd.repository.dao.GatewayDao;
import com.igoosd.service.GatewayService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GatewayServiceImpl extends AbsCommonService<Gateway, Long> implements GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;
    @Autowired
    private GatewayDao gatewayDao;


    @Override
    protected JpaRepository<Gateway, Long> getRepository() {
        return this.gatewayRepository;
    }

    @Override
    public Gateway getGatewayByCode(String code) {
        Gateway temp = new Gateway();
        temp.setCode(code);
        return gatewayRepository.findOne(Example.of(temp));
    }

    @Override
    public Page<Gateway> findPageList(Gateway gateway, Pageable pageable) {
        return gatewayDao.findPage(gateway, pageable);
    }
}
