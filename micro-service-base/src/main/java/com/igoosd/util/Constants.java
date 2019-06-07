package com.igoosd.util;

/**
 * 静态常量
 */
public class Constants {

    /**
     * 用户 Session Key
     */
    public static final String SESSION_USER_KEY = "user";

    /**
     * 验证码 Session Key
     */
    public static final String SESSION_CAPTCHA_KEY = "captcha";

    /**
     * 用户密码盐
     */
    public static final String USER_PASSWORD_SALT = "_website";

    /**
     * 是否显示验证码（0 显示；1 不显示）
     */
    public static final String CONFIG_SHOW_VERIFY = "show_verify";

    /**
     * 成功消息
     */
    public static final String MESSAGE_SUCCESS = "数据加载成功！";

    /**
     * 失败消息
     */
    public static final String MESSAGE_ERROR = "数据加载失败！";

    /**
     * 验证码不正确
     */
    public static final String MESSAGE_VERIFY_ERROR = "验证码不正确！";

    /**
     * 账号不能为空
     */
    public static final String MESSAGE_USERNAME_IS_NULL_ERROR = "账号不能为空！";

    /**
     * 帐号或密码不正确
     */
    public static final String MESSAGE_USERNAME_OR_PASSWORD_ERROR = "帐号或密码不正确！";

    /**
     * 成功
     */
    public static final int INT_200 = 200;

    /**
     * 失败
     */
    public static final int INT_MINUS_200 = -200;

    /**
     * 非法的请求，加密或签名验证失败
     */
    public static final int INT_201 = 201;

    /**
     * 操作失败
     */
    public static final int INT_202 = 202;

    /**
     * 设备未授权
     */
    public static final int INT_203 = 203;

    /**
     * 设备待授权
     */
    public static final int INT_204 = 204;

    /**
     * 设备已授权
     */
    public static final int INT_205 = 205;

    /**
     * 授权 key
     */
    public static final String AUTH_KEY = "authKey";

    /**
     * SessionId
     */
    public static final String SESSION_ID = "sessionId";

    /**
     * 安全密匙（24位）
     */
    public static final String SECRET_KEY = "#rSgRKVopFWbEevOy.igoosd";

    /**
     * 一天
     */
    public static final Long ONE_DAY = 24 * 60 * 60L;

    /**
     * 屏幕初始化参数分割符
     */
    public static String INIT_PAGE_PARAM_SPLIT = ",";


    /**
     * hash 存储 地磁接入数据信息 ${key} - {field:gatewayId}-{value:json(flowInfo)}
     */
    public static String REDIS_HASH_KEY_FLOW_INFO = "flow_info";


    public static final String CONFIG_ZERO_CAR_NUM_MEDIA_INFO = "zero_car_num_media_info";

    public static final String DEFAULT_PROGRAM_CONTENT_PRE_CONFIG_KEY = "screen_";

}
