package com.igoosd.service.impl;

import com.igoosd.domain.Config;
import com.igoosd.repository.ConfigRepository;
import com.igoosd.service.ConfigService;
import com.igoosd.util.AbsCommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfigServiceImpl extends AbsCommonService<Config, Long> implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    protected JpaRepository<Config, Long> getRepository() {
        return configRepository;
    }

    @Override
    public Config getByName(String name) {
        return configRepository.getByName(name);
    }
}
