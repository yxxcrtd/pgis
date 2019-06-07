package com.igoosd.http;

import com.igoosd.http.vo.RealTimeVo;
import com.igoosd.protocol.SysolutionProtocol;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RealTimeService {

    /**
     * 清除顶层屏幕信息
     * @param controlCardCode
     * @param sysolutionProtocl
     * @return
     */
    @POST("command/{controlCardCode}")
    Call<RealTimeVo> callRealTimeService(@Path("controlCardCode") String controlCardCode, @Body SysolutionProtocol sysolutionProtocl);

}
