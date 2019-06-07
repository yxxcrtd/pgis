package com.igoosd.schedule;

import com.igoosd.repository.GeoSensorRepository;
import com.igoosd.util.FlowInfoVo;
import com.igoosd.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.igoosd.util.Constants.REDIS_HASH_KEY_FLOW_INFO;

/**
 * 维护地磁物理状态及电量信息
 */
@Component
@Slf4j
public class GeoSensorStatusCheckTask {


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private GeoSensorRepository geomagneticSensorRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkGeoSensorStatus() {
        log.info("开始执行地磁状态检查调度....");
        Map<Object, Object> map = redisTemplate.opsForHash().entries(REDIS_HASH_KEY_FLOW_INFO);
        if (null != map && !map.isEmpty()) {
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                FlowInfoVo flowInfoVo = GsonUtil.parseJsonToObject(entry.getValue().toString(), FlowInfoVo.class);
                int gatewayNum = flowInfoVo.getGatewayNum();
                geomagneticSensorRepository.updateGeoSensorEQ(gatewayNum + "" + Integer.valueOf("A", 16), flowInfoVo.getEqOfSensorA());
                geomagneticSensorRepository.updateGeoSensorEQ(gatewayNum + "" + Integer.valueOf("B", 16), flowInfoVo.getEqOfSensorB());
                geomagneticSensorRepository.updateGeoSensorEQ(gatewayNum + "" + Integer.valueOf("C", 16), flowInfoVo.getEqOfSensorC());
                geomagneticSensorRepository.updateGeoSensorEQ(gatewayNum + "" + Integer.valueOf("D", 16), flowInfoVo.getEqOfSensorD());
            }
        }
        log.info("结束地磁状态检查调度，检查完毕....");
    }

}

