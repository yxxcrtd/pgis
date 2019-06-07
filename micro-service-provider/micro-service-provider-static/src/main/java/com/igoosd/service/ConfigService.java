package com.igoosd.service;

import com.igoosd.domain.Config;
import com.igoosd.util.CommonService;

public interface ConfigService extends CommonService<Config, Long> {


    /**
     * 根据名称查找 Config
     * @param name
     * @return
     */
    Config getByName(String name);
}
