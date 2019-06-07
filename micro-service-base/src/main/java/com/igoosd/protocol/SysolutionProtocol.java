package com.igoosd.protocol;

import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * 上海熙讯控制卡接口协议
 */
@Data
public class SysolutionProtocol {
    private String _id = UUID.randomUUID().toString().replaceAll("-","");
    private String type;
    private String url;
    private String js;
    private Boolean persistent;
    private String method;
    private Integer num;
    private String html;
    private Integer interval;//间隔
    private Integer step;//步进距离
    private String direction;//水平滚动  left / right
    private String align;//垂直位置 top、center、bottom
    private String apk;
    private String fn;
    private List<String> list;
    private List<String> pathList;
    private Integer width;
    private Integer height;
    private Integer top;
    private Integer left;
    private Integer index;
    private String fileName;
    private String content;
    private String path;
    private Boolean stop;//是否停止默认播放器
    private Object arg;
    private Object arg1;//开关屏幕(boolean ) 、亮度（1~8 整数）、音量（0~15整数） 其他
    private Object arg2;
    private String publicKey;
    private String command;
    private String sign;
    private String _type;


    public SysolutionProtocol(SysolutionProtoclTypeEnum typeEnum) {
        this.type = typeEnum.getType();
        this.fn= typeEnum.getFn();
    }


    /**
     * 熙讯协议
     */
    public static enum SysolutionProtoclTypeEnum {

        loadUrl("loadUrl", "加载顶层网页（显示在其他界面之上，默认是透明的）"),
        invokeJs("invokeJs", "调用“顶层网页”里的js方法"),
        invokeBuildInJs("invokeBuildInJs", "滚动文字"),
        clear("clear", "清屏，清除顶层网页内容"),
        startActivity("startActivity", "启动xwalk（需要先在www.m2mled.net上安装xwalk，xwalk是一个支持html5的浏览器）"),
        callXwalkFnForLoadUrl("callXwalkFn", "loadUrl", "使用xwalk加载网页"),
        callXwalkFnJs("callXwalkFn", "callFnInLoaded", "调用xwalk加载的网页里的js方法"),
        setPlayList("setPlayList", "播放视频列表"),
        clearPlayList("clearPlayList", "清除视频列表(从conn-9.6.0起支持)"),
        videoPause("pause", "暂停视频(从conn-9.6.0起支持)"),
        videoContinue("continue", "继续播放视频(从conn-9.6.0起支持)"),
        videoPlay("play", "点播视频(从conn-9.6.0起支持)"),
        saveStringFile("saveStringFile", "以字符串形式上传并保存html、图片等文件到sd卡"),
        deleteFile("deleteFile", "删除以字符串形式上传的文件"),
        downloadFileToSD("downloadFileToSD", "下载文件到控制卡，不插SD卡时，将存到内部存储（图片、视频等任意文件）"),
        deleteFileFromSD("deleteFileFromSD", "删除SD上的文件"),
        getFileLength("getFileLength", "获取SD卡上文件的大小,本协议可以用来验证文件上传（下载）有没有成功"),
        stopPlayer("stopPlayer", "停止默认播放器 (xixunplayer)"),
        getGpsLocation("getGpsLocation", "获取GPS坐标(from conn-v9.3.7)"),
        callCardServiceForSetScreenOpen("callCardService", "setScreenOpen", "开关屏幕(from conn-v9.3.7)"),
        callCardServiceForIsScreenOpen("callCardService", "isScreenOpen", "获取屏幕是否打开"),
        callCardServiceForGetBrightness("callCardService", "getBrightness", "获取屏幕亮度"),
        callCardServiceForSetVolume("callCardService", "setVolume", "设置屏幕音量"),
        callCardServiceForGetVolume("callCardService", "getVolume", "获取屏幕音量"),
        callCardServiceForGetScreenWidth("callCardService", "getScreenWidth", "获取屏幕宽度"),
        callCardServiceForGetScreenHeight("callCardService", "getScreenHeight", "获取屏幕高度"),
        callCardServiceForGetNetworkType("callCardService", "getNetworkType", "获取网络类型"),
        callCardServiceForSetTimeSync("callCardService", "setTimeSync", "设置NTP服务器或时区"),
        callCardServiceForGetTimeSync("callCardService", "getTimeSync", "获取NTP服务器或时区"),
        callCardServiceForScreenshot("callCardService", "screenshot", "获取截图"),
        callCardServiceForReboot("callCardService", "reboot", "重启"),
        getPackageVersion("getPackageVersion", "获取APK信息"),
        callXwalkFnForSetBackgroundColor("callXwalkFn", "setBackgroundColor", "设置背景"),
        setPublicKey("setPublicKey", "设置公钥开启安全通信模式"),
        executeCommandSecurely("executeCommandSecurely", "安全执行命令（对原本的数据命令签名并把它包装在此协议命令里）"),
        callCardService("callCardService", "getFpgaInfomation", "获取硬件状态"),
        callLiveServiceForStartLiveVideo("callLiveService", "播放流地址（支持rtmp，rtsp协议）(from conn-v9.6.9)"),
        callLiveServiceForStopLiveVideo("callLiveService", "停止播放流地址(from conn-v9.6.9)");


        private String type;
        private String name;
        private String fn;

        SysolutionProtoclTypeEnum(String type, String fn, String name) {
            this.type = type;
            this.name = name;
            this.fn = fn;
        }

        SysolutionProtoclTypeEnum(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getFn() {
            return fn;
        }
    }
}
