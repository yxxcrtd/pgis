package com.igoosd.util;

import lombok.Data;
import lombok.ToString;

/**
 *  网关数据实体model
 */
@Data
@ToString
public class FlowInfoVo {


    private String key;//消息唯一标识
    /**
     * 网关号
     */
    private Integer gatewayNum;
    /**
     * 网关电量
     */
    private Integer eqOfGateway;

    /**
     *  驶入车流量
     */
    private Integer enterCarFlow;

    private Integer exitCarFlow;

    /**
     * 探测器A 电量
     */
    private Integer eqOfSensorA;

    /**
     * 探测器B 电量
     */
    private Integer eqOfSensorB;

    /**
     * 探测器C 电量
     */
    private Integer eqOfSensorC;
    /**
     * 探测器D 电量
     */
    private Integer eqOfSensorD;

}
