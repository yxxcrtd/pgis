package com.igoosd.http;

import com.igoosd.http.vo.RealTimeVo;
import com.igoosd.protocol.SysolutionProtocol;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainTest {


    public static void main(String[] args) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.198.2.158:15498/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RealTimeService realTimeService = retrofit.create(RealTimeService.class);
        SysolutionProtocol protocl = new SysolutionProtocol(SysolutionProtocol.SysolutionProtoclTypeEnum.invokeJs);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int carNum = 100;
            @Override
            public void run() {
                --carNum;
                carNum = carNum <0 ? 100 : carNum;
                protocl.setJs("changeText("+carNum+","+50+")");
                System.out.println("准备调用。。。");
                Call<RealTimeVo> call = realTimeService.callRealTimeService("e10-a16-00137",protocl);
                try {
                    Response<RealTimeVo> realTimeVoResponse = call.execute();
                    System.out.println(realTimeVoResponse);
                    System.out.println(realTimeVoResponse.body());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("....");
                }

            }
        },0,1000);
    }
}
