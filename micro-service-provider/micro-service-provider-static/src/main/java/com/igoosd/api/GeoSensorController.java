package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.GeoSensor;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.enumeration.DeviceStatusEnum;
import com.igoosd.service.GeoSensorService;
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

@Api(tags = "地磁接口")
@RestController
@RequestMapping("/api/geoSensor")
public class GeoSensorController extends BaseController<GeoSensor> {

    @Autowired
    private GeoSensorService geoSensorService;


    @ApiOperation(value = "新增地磁", notes = "编码（必填）、类型（必填、下拉框）、所属网关(必填、下拉框)、设备状态（默认1正常）")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody GeoSensor sensor) {
        Assert.hasText(sensor.getCode(), "编码不能为空");
        Assert.notNull(sensor.getType(), "类型不能为空");
        Assert.notNull(sensor.getGatewayId(), "所属网关不能为空");
        ResultMsg<?> resultMsg = verifyCode(sensor);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }

        sensor.setCreateTime(new Date());
        sensor.setDeviceStatus(DeviceStatusEnum.NORMAL.getValue());
        sensor.setBatteryCapacity(100);
        geoSensorService.insert(sensor);
        return ResultMsg.resultSuccess("新增地磁成功");
    }

    @ApiOperation(value = "更新地磁", notes = "id（必填、隐藏）、编码（必填）、类型（必填、下拉框）、所属网关(必填、下拉框)、设备状态（默认1正常）")
    @PostMapping("/update")
    public ResultMsg<?> update(@RequestBody GeoSensor sensor) {
        Assert.hasText(sensor.getCode(), "编码不能为空");
        Assert.notNull(sensor.getType(), "类型不能为空");
        Assert.notNull(sensor.getGatewayId(), "所属网关不能为空");
        Assert.notNull(sensor.getId(), "ID不能为空");
        ResultMsg<?> resultMsg = verifyCode(sensor);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        geoSensorService.update(sensor);
        return ResultMsg.resultSuccess("更新地磁成功");
    }

    @ApiOperation(value = "编码唯一性校验", notes = "编码、id")
    @PostMapping("/verifyCode")
    public ResultMsg<?> verifyCode(@RequestBody GeoSensor sensor) {
        Assert.hasText(sensor.getCode(), "编码不能为空");
        GeoSensor temp = new GeoSensor();
        temp.setCode(sensor.getCode());
        List<GeoSensor> list = geoSensorService.findAll(temp);
        return verifyCodeForList(list, sensor);
    }


    @LogMonitor(false)
    @ApiOperation(value = "分页查询地磁列表", notes = "查询条件：编码、网关(下拉框)")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<Page<GeoSensor>> findPageList(@RequestBody GeoSensor sensor, @PathVariable int page, @PathVariable int rows) {
        Page<GeoSensor> rstPage = geoSensorService.findPageList(sensor, new PageRequest(page - 1, rows, Sort.Direction.DESC, "createTime"));
        return ResultMsg.resultSuccess(rstPage);
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<GeoSensor> getEntityById(@PathVariable("id") Long id) {
        GeoSensor gs = geoSensorService.getEntityById(id);
        return ResultMsg.resultSuccess(gs);
    }
}
