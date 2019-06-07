package com.igoosd.service;

import com.igoosd.domain.User;
import com.igoosd.util.CommonService;

/**
 * 用户接口
 */
public interface UserService extends CommonService<User, Long> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

}
