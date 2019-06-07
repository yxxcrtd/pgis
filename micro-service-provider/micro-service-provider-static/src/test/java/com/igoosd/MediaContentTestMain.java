package com.igoosd;

import com.igoosd.http.RealTimeService;
import com.igoosd.http.vo.RealTimeVo;
import com.igoosd.protocol.SysolutionProtocol;
import com.igoosd.util.GsonUtil;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class MediaContentTestMain {


    public static void main(String[] args) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.198.2.158:15498/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RealTimeService realTimeService = retrofit.create(RealTimeService.class);

        //clearScreen(realTimeService);
        //loadUrl(realTimeService);
        SysolutionProtocol protocol = new SysolutionProtocol(SysolutionProtocol.SysolutionProtoclTypeEnum.invokeBuildInJs);
        protocol.setMethod("scrollMarquee");
        protocol.setNum(-1);
        protocol.setInterval(1); //步进间隔  毫秒
        protocol.setStep(1);
        protocol.setDirection("left");
        protocol.setAlign("top");
        protocol.setHtml("<p>\n" +
                "    <span style=\"color: rgb(255, 0, 0); font-size: 16px;\"><strong>建设中国特色主义新中国</strong></span><br/>\n" +
                "</p>");
        System.out.println(GsonUtil.toJson(protocol));
        Call<RealTimeVo> call = realTimeService.callRealTimeService("e10-a16-00017",protocol);
        Response<RealTimeVo> response = call.execute();


        System.out.println(response);
        System.out.println(response.body());

    }

    /**
     * 清除顶层网页内容
     */
    public static void clearScreen(RealTimeService realTimeService) throws IOException {
        SysolutionProtocol protocol = new SysolutionProtocol(SysolutionProtocol.SysolutionProtoclTypeEnum.clear);
        Call call = realTimeService.callRealTimeService("e10-a16-00017",protocol);
        call.execute();
    }

    public static void loadUrl(RealTimeService realTimeService) throws IOException {
        SysolutionProtocol protocol = new SysolutionProtocol(SysolutionProtocol.SysolutionProtoclTypeEnum.loadUrl);
        protocol.setUrl("http://file.igoosd.com/empty.html");
        Call call = realTimeService.callRealTimeService("e10-a16-00017",protocol);
        call.execute();
    }
}
