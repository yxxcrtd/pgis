package com.igoosd.api;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.igoosd.domain.Config;
import com.igoosd.domain.User;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.service.ConfigService;
import com.igoosd.service.UserService;
import com.igoosd.util.Constants;
import com.igoosd.util.EncryptUtil;
import com.igoosd.util.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.igoosd.domain.vo.ResultMsg.resultFail;
import static com.igoosd.domain.vo.ResultMsg.resultSuccess;
import static com.igoosd.util.Constants.CONFIG_SHOW_VERIFY;
import static com.igoosd.util.Constants.INT_MINUS_200;
import static com.igoosd.util.Constants.MESSAGE_USERNAME_IS_NULL_ERROR;
import static com.igoosd.util.Constants.MESSAGE_USERNAME_OR_PASSWORD_ERROR;
import static com.igoosd.util.Constants.MESSAGE_VERIFY_ERROR;
import static com.igoosd.util.Constants.SESSION_CAPTCHA_KEY;
import static com.igoosd.util.WebUtils.getIP;

/**
 * Login API
 */
@Slf4j
@Api(tags = "登录")
@RestController
@RequestMapping(value = "api/user", produces = {"application/json;charset=UTF-8"})
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private DefaultKaptcha captchaProducer;

    /**
     * Login Check
     *
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST")
    @PostMapping("login")
    ResultMsg login(@RequestBody User user, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        if (StringUtils.isBlank(user.getUsername())) {
            return resultFail(INT_MINUS_200, MESSAGE_USERNAME_IS_NULL_ERROR);
        }

        Config config = new Config();
        config.setName(CONFIG_SHOW_VERIFY);
        Config c = (Config) configService.findAll(config).get(0);
        log.info("打印是否显示验证码：{}",c);
        System.out.println("验证码配置信息:"+c.getValue());
        if ("0".equalsIgnoreCase(c.getValue())) {
            String captcha = String.valueOf(request.getSession().getAttribute(SESSION_CAPTCHA_KEY));
            if (!captcha.equalsIgnoreCase(user.getVerifyCode())) {
                return resultFail(INT_MINUS_200, MESSAGE_VERIFY_ERROR);
            }
        }

        User u = userService.getUserByUsername(user.getUsername());
        if (null == u) {
            return resultFail(INT_MINUS_200, MESSAGE_USERNAME_OR_PASSWORD_ERROR);
        } else {
            if (!MD5.toMD5(user.getPassword() + u.getSalt()).equals(u.getPassword())) {
                return resultFail(INT_MINUS_200, MESSAGE_USERNAME_OR_PASSWORD_ERROR);
            }

            u.setLastLoginIp(getIP(request));
            u.setLastLoginTime(new Date());
            userService.update(u);
        }

        String authKey = EncryptUtil.encryptBase64(user.getUsername() + "|" + user.getPassword(), Constants.SECRET_KEY);
        data.put("rememberKey", authKey);
        data.put("authKey", authKey);
        data.put("sessionId", request.getSession().getId());
        data.put("userInfo", u);

        return resultSuccess("登录成功！", data);
    }

    /**
     * Verify Code
     */
    @ApiOperation(value = "验证码", notes = "验证码", httpMethod = "GET")
    @GetMapping("verify")
    void verify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        log.info("验证码：" + capText);

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_CAPTCHA_KEY, capText);

        try {
            String uuid = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("captchaCode", uuid);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

}
