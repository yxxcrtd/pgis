package com.igoosd.http;

import com.igoosd.http.vo.RealTimeVo;
import com.igoosd.protocol.SysolutionProtocol;
import com.igoosd.util.GsonUtil;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfiguration {

    @Value("${retrofit.base-url}")
    private String retrofitBaseUrl;

    @Value("${retrofit.timeout}")
    private int timeout;

    /**
     * 熙讯sysolution RetrofitInstance
     *
     * @return
     */
    @Bean
    public Retrofit buildRetrofitInstanceForSysolution() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, TimeUnit.SECONDS);
        builder.readTimeout(timeout, TimeUnit.SECONDS);
        builder.writeTimeout(timeout,TimeUnit.SECONDS);
        OkHttpClient client =builder.build();
        return new Retrofit.Builder()
                .baseUrl(retrofitBaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public RealTimeService createHttpService() {
        return buildRetrofitInstanceForSysolution().create(RealTimeService.class);
    }

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.198.2.158:15498/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

      RealTimeService realTimeService = retrofit.create(RealTimeService.class);
          SysolutionProtocol protocl = new SysolutionProtocol(SysolutionProtocol.SysolutionProtoclTypeEnum.loadUrl);
        protocl.setPersistent(true);
        protocl.setUrl("http://file.igoosd.com/12801024.html?initText=112212");
      /*  SysolutionProtocol protocl = new SysolutionProtocol(SysolutionProtocol.SysolutionProtoclTypeEnum.clear);*/
        String jsonStr = GsonUtil.toJson(protocl);
        System.out.println(jsonStr);
        Call<RealTimeVo> call = realTimeService.callRealTimeService("e10-a16-00017",protocl);
        Response<RealTimeVo> response = call.execute();
        System.out.println(response.toString());
        System.out.println(response.body());
    }

}
