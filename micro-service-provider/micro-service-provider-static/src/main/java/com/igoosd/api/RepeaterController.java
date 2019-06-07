package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.Repeater;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.enumeration.DeviceStatusEnum;
import com.igoosd.service.RepeaterService;
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
 * 中继器接口
 */
@Api(tags = "中继器接口")
@RestController
@RequestMapping("/api/repeater")
public class RepeaterController extends BaseController<Repeater> {

    @Autowired
    private RepeaterService repeaterService;

    @ApiOperation(value = "新增中继器", notes = "编码(必填)、网关(必填、下拉框)、设备状态(默认1 正常)、描述")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody Repeater repeater) {
        Assert.hasText(repeater.getCode(), "编码不能为空");
        Assert.notNull(repeater.getGatewayId(), "网关不能为空");

        ResultMsg<?> resultMsg = verifyCode(repeater);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        repeater.setCreateTime(new Date());
        repeater.setDeviceStatus(DeviceStatusEnum.NORMAL.getValue());
        repeaterService.insert(repeater);
        return ResultMsg.resultSuccess("新增中继器成功");
    }

    @ApiOperation(value = "更新中继器", notes = "id(必填、隐藏)、编码(必填)、网关(必填、下拉框)、设备状态(默认1 正常)、描述")
    @PostMapping("/update")
    public ResultMsg<?> update(@RequestBody Repeater repeater) {
        Assert.hasText(repeater.getCode(), "编码不能为空");
        Assert.notNull(repeater.getGatewayId(), "网关不能为空");
        Assert.notNull(repeater.getId(), "ID不能为空");
        ResultMsg<?> resultMsg = verifyCode(repeater);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        repeaterService.update(repeater);
        return ResultMsg.resultSuccess("更新中继器成功");
    }

    @LogMonitor(false)
    @ApiOperation(value = "编码唯一性校验")
    @PostMapping("/verifyCode")
    public ResultMsg<?> verifyCode(@RequestBody Repeater repeater) {
        Assert.hasText(repeater.getCode(), "编码不能为空");
        Repeater temp = new Repeater();
        temp.setCode(repeater.getCode());
        List<Repeater> list = repeaterService.findAll(temp);
        return verifyCodeForList(list, repeater);
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查询中继器列表", notes = "查询条件:编码、网关")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<Page<Repeater>> findPageList(@RequestBody Repeater repeater, @PathVariable int page, @PathVariable int rows) {
        Page<Repeater> rstPage = repeaterService.findPageList(repeater, new PageRequest(page - 1, rows, Sort.Direction.DESC, "createTime"));
        return ResultMsg.resultSuccess(rstPage);
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<Repeater> getEntityById(@PathVariable("id") Long id) {
        Repeater p = repeaterService.getEntityById(id);
        return ResultMsg.resultSuccess(p);
    }
}
