package com.igoosd.api;

import com.igoosd.biz.AcceptorService;
import com.igoosd.config.LogMonitor;
import com.igoosd.domain.ControlCard;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.enumeration.DeviceStatusEnum;
import com.igoosd.service.ControlCardService;
import com.igoosd.util.Assert;
import com.igoosd.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 控制卡接口
 */
@Api(tags = "控制卡管理接口")
@RestController
@RequestMapping("/api/controlCard")
public class ControlCardController {

    @Autowired
    private ControlCardService controlCardService;

    @Autowired
    private AcceptorService acceptorService;


    @ApiOperation(value = "新增控制卡")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody ControlCard cc) {
        Assert.hasText(cc.getCode(), "控制卡编码不能为空");
        Assert.notNull(cc.getSimCardId(), "sim卡不能为空");
        ResultMsg<?> resultMsg = verifyCode(cc);
        if (resultMsg.getCode() != Constants.INT_200) {
            return resultMsg;
        }
        cc.setCreateTime(new Date());
        cc.setDeviceStatus(DeviceStatusEnum.NORMAL.getValue());
        cc.setInitStatus(0);
        controlCardService.insert(cc);
        return ResultMsg.resultSuccess("新增控制卡成功");
    }

    @ApiOperation(value = "更新控制卡")
    @PostMapping("/update")    public ResultMsg<?> update(@RequestBody ControlCard cc) {
        Assert.notNull(cc.getId(), "id不能为空");
        Assert.notNull(cc.getSimCardId(), "sim卡不能为空");
        Assert.hasText(cc.getCode(), "编码不能为空");
        ResultMsg<?> resultMsg = verifyCode(cc);
        if (resultMsg.getCode() != Constants.INT_200) {
            return resultMsg;
        }
        controlCardService.update(cc);
        return ResultMsg.resultSuccess("更新控制卡成功");
    }


    @LogMonitor(false)
    @ApiOperation(value = "验证编码唯一")
    @PostMapping("/verifyCode")
    public ResultMsg<?> verifyCode(@RequestBody ControlCard cc) {
        Assert.hasText(cc.getCode(), "编码不能为空");
        ControlCard temp = new ControlCard();
        temp.setCode(cc.getCode());
        List<ControlCard> list = controlCardService.findAll(temp);
        if (!CollectionUtils.isEmpty(list)) {
            if (cc.getId() == null) {
                return ResultMsg.resultFail("编码不唯一");
            } else {
                if (!cc.getId().equals(list.get(0).getId())) {
                    return ResultMsg.resultFail("编码不唯一");
                }
            }
        }
        return ResultMsg.resultSuccess();
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查询控制卡列表")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<Page<ControlCard>> findPageList(@RequestBody ControlCard cc, @PathVariable int page, @PathVariable int rows) {
        Page<ControlCard> rstPage = controlCardService.findPageList(cc, new PageRequest(page - 1, rows, Sort.Direction.DESC, "createTime"));
        return ResultMsg.resultSuccess(rstPage);
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<ControlCard> getEntityById(@PathVariable("id") Long id) {
        ControlCard cc = controlCardService.getEntityById(id);
        return ResultMsg.resultSuccess(cc);
    }

    @ApiOperation(value = "初始化控制卡",notes = "初始化按钮无是否成功，均保留")
    @PostMapping("/init/{id}")
    public ResultMsg<?> initControlCard(@PathVariable("id") Long id){
        acceptorService.initControlCard(id);
        return ResultMsg.resultSuccess("初始化操作完成");
    }


    @LogMonitor(false)
    @ApiOperation(value = "查询所有控制卡列表",notes = "下拉框使用")
    @PostMapping("/list")
    public ResultMsg<List<ControlCard>> findAll() {
        List<ControlCard> list = controlCardService.findAll();
        return ResultMsg.resultSuccess(list);
    }
}
