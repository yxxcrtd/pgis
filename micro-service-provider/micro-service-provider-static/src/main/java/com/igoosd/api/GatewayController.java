package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.Gateway;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.enumeration.DeviceStatusEnum;
import com.igoosd.service.GatewayService;
import com.igoosd.util.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 网关控制
 */
@Api(tags = "网关接口")
@RestController
@RequestMapping("/api/gateway")
public class GatewayController extends BaseController<Gateway> {

    @Autowired
    private GatewayService gatewayService;


    @ApiOperation(value = "新增网关", notes = "编码（必填）、sim卡（必填，下拉框）、停车场（必填、下拉框）、设备状态（默认1 正常）、描述")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody Gateway gateway) {
        Assert.hasText(gateway.getCode(), "编码不能为空");
        Assert.notNull(gateway.getSimCardId(), "sim卡不能为空");
        Assert.notNull(gateway.getParkingId(), "停车场不能为空");

        ResultMsg<?> resultMsg = verifyCode(gateway);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }

        gateway.setDeviceStatus(DeviceStatusEnum.NORMAL.getValue());
        gateway.setCreateTime(new Date());
        gatewayService.insert(gateway);
        return ResultMsg.resultSuccess("新增网关成功");
    }

    @ApiOperation(value = "更新网关", notes = "id（必填、隐藏）编码（必填）、sim卡（必填，下拉框）、停车场（必填、下拉框）、设备状态（默认1 正常）、描述")
    @PostMapping("/update")
    public ResultMsg<?> update(@RequestBody Gateway gateway) {
        Assert.hasText(gateway.getCode(), "编码不能为空");
        Assert.notNull(gateway.getSimCardId(), "sim卡不能为空");
        Assert.notNull(gateway.getParkingId(), "停车场不能为空");
        Assert.notNull(gateway.getId(), "id不能为空");
        ResultMsg<?> resultMsg = verifyCode(gateway);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        gatewayService.update(gateway);
        return ResultMsg.resultSuccess("更新网关成功");
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查询网关列表", notes = "查询条件: 编码、sim卡（下拉框）、停车场(下拉框)")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<Page<Gateway>> findPageList(@RequestBody Gateway gateway, @PathVariable int page, @PathVariable int rows) {
        Page<Gateway> rstPage = gatewayService.findPageList(gateway, new PageRequest(page - 1, rows, Sort.Direction.DESC, "createTime"));
        return ResultMsg.resultSuccess(rstPage);
    }

    @LogMonitor(false)
    @ApiOperation(value = "编码唯一性校验", notes = "编码、id")
    @PostMapping("/verifyCode")
    public ResultMsg<?> verifyCode(@RequestBody Gateway gateway) {
        Assert.hasText(gateway.getCode(), "编码不能为空");
        Gateway temp = new Gateway();
        temp.setCode(gateway.getCode());
        List<Gateway> list = gatewayService.findAll(temp);
        return verifyCodeForList(list, gateway);
    }

    @LogMonitor(false)
    @ApiOperation(value = "网关列表")
    @PostMapping("/list")
    public ResultMsg<List<Gateway>> findList() {
        List<Gateway> list = gatewayService.findAll();
        return ResultMsg.resultSuccess(list);
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<Gateway> getEntityById(@PathVariable("id") Long id) {
        Gateway gw = gatewayService.getEntityById(id);
        return ResultMsg.resultSuccess(gw);
    }
}
