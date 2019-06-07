package com.igoosd.service;

import com.igoosd.domain.Gateway;
import com.igoosd.util.CommonService;

public interface GatewayService extends CommonService<Gateway, Long> {

    /**
     * 根据网关编码查询网关信息
     * @param code
     * @return
     */
    Gateway getGatewayByCode(String code);

}
