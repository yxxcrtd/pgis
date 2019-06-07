package com.igoosd.interceptor;

import com.igoosd.domain.User;
import com.igoosd.service.UserService;
import com.igoosd.util.Constants;
import com.igoosd.util.EncryptUtil;
import com.igoosd.util.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求URL：" + request.getRequestURI());

        String authKey = request.getHeader(Constants.AUTH_KEY);
        log.info("AuthKey：" + authKey);

        String sessionId = request.getHeader(Constants.SESSION_ID);
        log.info("SessionId：" + sessionId);

        if ("undefined".equalsIgnoreCase(authKey) || "undefined".equalsIgnoreCase(sessionId)) {
            return false;
        }

        HttpSession session = request.getSession();
        // 校验 SessionId 和 AuthKey
        if (StringUtils.isEmpty(authKey) || StringUtils.isEmpty(sessionId)) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(FastJsonUtils.resultError(-100, "AuthKey 或 SessionId不能为空！", null));
            writer.flush();
            return false;
        }

        // 检查账号有效性
        User user = (User) session.getAttribute(Constants.SESSION_USER_KEY);
        if (null == user) {
            String decryptAuthKey = EncryptUtil.decryptBase64(authKey, Constants.SECRET_KEY);
            if (null != decryptAuthKey) {
                String[] auths = decryptAuthKey.split("\\|");
                String username = auths[0];
                user = userService.getUserByUsername(username);
                session.setAttribute(Constants.SESSION_USER_KEY, user);
            } else {
                return false;
            }
        }

        return true;
    }

}
