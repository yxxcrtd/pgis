package com.igoosd.biz;

import com.igoosd.exception.StaticException;
import com.igoosd.http.RealTimeService;
import com.igoosd.http.vo.RealTimeVo;
import com.igoosd.protocol.SysolutionProtocol;
import com.igoosd.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;


@Service
@Slf4j
public class ScreenSendService {

    @Autowired
    private RealTimeService realTimeService;

    @Autowired
    ScreenInitProperties screenInitProperties;

    /**
     * 指定控制卡 load 页面 url
     *
     * @param controlCardCode
     * @param url
     */
    public void loadUrlForScreen(String controlCardCode, String url) {
        SysolutionProtocol protocol = new SysolutionProtocol(SysolutionProtocol.SysolutionProtoclTypeEnum.loadUrl);
        protocol.setUrl(url);
        protocol.setPersistent(true); // 持久化操作
        Call<RealTimeVo> call = realTimeService.callRealTimeService(controlCardCode, protocol);
        log.info("控制卡：{}，协议：{}，call：{}", controlCardCode, protocol, call);

        try {
            Response<RealTimeVo> response = call.execute();
            log.info("调用 RealtimeServer 返回：{}", response);

            Assert.isTrue(200 == response.code(), "加载页面请求异常，请求响应代码：" + response.code());
            Assert.isTrue(response.body().get_type().equals("success"), "加载页面请求，控制卡端业务异常");
        } catch (IOException e) {
            log.error("控制卡:{}，加载页面url:{} 出现异常 ！！！！！！！！！！！！！！！！！！！！！！      {}", controlCardCode, url, e);
            throw new StaticException("控制卡加载页面异常", e);
        }
    }

    /**
     * 发送 剩余车位数到指定控制卡上
     *
     * @param lotRemainCount
     * @return
     */
    public RealTimeVo sendLotRemainCount(int lotRemainCount, String controlCardCode, Long screenId) {
        SysolutionProtocol protocol = new SysolutionProtocol(SysolutionProtocol.SysolutionProtoclTypeEnum.invokeJs);
        protocol.setJs("changeText(" + screenId + "," + lotRemainCount + ")");
        Call<RealTimeVo> call = realTimeService.callRealTimeService(controlCardCode, protocol);
        return parseVo("车位数发送到屏幕异常",call);
    }

    private RealTimeVo parseVo(String expReason,Call<RealTimeVo> call){
        RealTimeVo vo = new RealTimeVo();
        try {
            Response<RealTimeVo> response = call.execute();
            if (response.code() == 200) {
                return response.body();
            }
            vo.set_type("false");
            vo.setDesc(response.code()+"");
            return vo;
        } catch (IOException e) {
            log.error(expReason, e);
            vo.set_type("false");
            vo.setDesc("IO异常");
            return vo;
        }
    }

}
