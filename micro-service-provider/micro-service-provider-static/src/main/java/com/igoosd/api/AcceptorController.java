package com.igoosd.api;

import com.igoosd.biz.AcceptorService;
import com.igoosd.domain.Parking;
import com.igoosd.domain.vo.LotRemainVo;
import com.igoosd.domain.vo.RegisterVo;
import com.igoosd.service.ParkingService;
import com.igoosd.util.Assert;
import com.igoosd.util.FlowInfoVo;
import com.igoosd.util.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地磁接入回调
 */
@RestController
@RequestMapping("/acceptor")
@Slf4j
public class AcceptorController {


    @Autowired
    private AcceptorService acceptorService;
    @Autowired
    private ParkingService parkingService;



    /**
     * 地磁信息回调接口
     *
     * @param flowInfoVo
     * @return
     */
    @PostMapping("/flow")
    public ResultVo getFlowInfo(@RequestBody FlowInfoVo flowInfoVo) {
        try {
            Assert.notNull(flowInfoVo.getGatewayNum(),"网关号为空");
            Assert.notNull(flowInfoVo.getEnterCarFlow(),"驶入量不能为空");
            Assert.notNull(flowInfoVo.getExitCarFlow(),"驶出量不能为空");
            //更新停车场信息
            acceptorService.doFlowInfo(flowInfoVo,true);
        } catch (Exception e) {
            log.error("业务异常,e",e);
           return ResultVo.getFailResult("业务异常...");
        }
        return ResultVo.getSuccessResult();
    }

    /**
     * 网关注册
     *
     */
    @PostMapping("/register")
    public ResultVo registerGateway(@RequestBody RegisterVo regVo){
        ResultVo vo = new ResultVo();
        boolean flag = false;
        if(!StringUtils.isEmpty(regVo.getGatewayCode())){
            flag = acceptorService.registerGateway(regVo.getGatewayCode());
        }
        vo.setSuccess(flag);
        return vo;
    }

    @PostMapping("/confirm")
    public ResultVo confirm(@RequestBody FlowInfoVo vo){
        try {
            acceptorService.confirm(vo);
        } catch (Exception e) {
            return ResultVo.getFailResult("服务端异常....");
        }
        return ResultVo.getSuccessResult();
    }

    @PostMapping("/lotRemainCount")
    public ResultVo getLotRemainCount(@RequestBody LotRemainVo remainVo){
        String  code = remainVo.getCode();
        Integer lotRemainCount = remainVo.getCarNum();
        Long timestamp = remainVo.getTimestamp();
        Assert.hasText(code,"不合法的参数，编码不能为空");
        Assert.notNull(lotRemainCount,"不合法的参数，剩余车位数不能为空");
        Assert.notNull(timestamp,"不合法的参数，时间戳不能为空");

        Parking temp = new Parking();
        temp.setCode(code);
        List<Parking> list = parkingService.findAll(temp);
        Assert.isTrue(!CollectionUtils.isEmpty(list),"不合法的参数，编码异常");
        Parking oldParking = list.get(0);
        Assert.isTrue(oldParking.getLotTotalCount() >= lotRemainCount && lotRemainCount >=0,"剩余停车位数不合法");
        //更新
        acceptorService.changeParkingLotRemainCount(lotRemainCount,oldParking.getId(),timestamp);
        return ResultVo.getSuccessResult();
    }
}
