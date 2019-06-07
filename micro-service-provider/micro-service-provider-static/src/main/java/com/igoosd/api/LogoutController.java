package com.igoosd.api;

import com.igoosd.domain.User;
import com.igoosd.domain.vo.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.igoosd.domain.vo.ResultMsg.resultSuccess;
import static com.igoosd.util.Constants.SESSION_USER_KEY;

/**
 * Logout
 */
@Slf4j
@Api(tags = "退出")
@RestController
@RequestMapping("api/user")
public class LogoutController {

    /**
     * Logout
     */
    @ApiOperation(value = "用户退出", notes = "退出", httpMethod = "POST")
    @PostMapping(value = "logout", produces = {"application/json;charset=UTF-8"})
    ResultMsg logout(HttpSession session) {
        String logString;
        User user = (User) session.getAttribute(SESSION_USER_KEY);
        if (null != user) {
            session.invalidate();
            logString = "用户：" + user.getUsername() + " 退出成功！";
        } else {
            logString = "登录用户已失效，退出成功！";
        }
        log.info(logString);
        return resultSuccess(logString);
    }

}
