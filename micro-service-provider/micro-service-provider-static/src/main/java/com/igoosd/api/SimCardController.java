package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.ControlCard;
import com.igoosd.domain.Gateway;
import com.igoosd.domain.SimCard;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.enumeration.DeviceStatusEnum;
import com.igoosd.service.ControlCardService;
import com.igoosd.service.GatewayService;
import com.igoosd.service.SimCardService;
import com.igoosd.util.Assert;
import com.igoosd.util.RegexUtils;
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
 * sim卡维护
 */
@Api(tags = "sim卡管理")
@RequestMapping("/api/simCard")
@RestController
public class SimCardController extends BaseController<SimCard> {

    @Autowired
    private SimCardService simCardService;
    @Autowired
    private ControlCardService controlCardService;
    @Autowired
    private GatewayService gatewayService;

    @ApiOperation(value = "新增SIM卡", notes = "手机号（必填、校验）、运营商（必填、下拉框）、网络模式（必填、下拉框）、备注")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody SimCard simCard) {
        Assert.hasText(simCard.getPhoneNumber(), "手机号不能为空");
        Assert.notNull(simCard.getOperatorType(), "运营商不能为空");
        Assert.notNull(simCard.getNetworkMode(), "网络制式不能为空");
        Assert.isTrue(RegexUtils.isPhone(simCard.getPhoneNumber()), "不合法的手机号");

        ResultMsg<?> resultMsg = verifyPhoneNumber(simCard);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        simCard.setDeviceStatus(DeviceStatusEnum.NORMAL.getValue());
        simCard.setCreateTime(new Date());
        simCardService.insert(simCard);
        return ResultMsg.resultSuccess("SIM卡新增成功");
    }

    @ApiOperation(value = "更新SIM卡", notes = "id(主键，必填，隐藏)、手机号（必填、校验）、运营商（必填、下拉框）、网络模式（必填、下拉框）、备注")
    @PostMapping("/update")
    public ResultMsg<?> update(@RequestBody SimCard simCard) {
        Assert.hasText(simCard.getPhoneNumber(), "手机号不能为空");
        Assert.notNull(simCard.getOperatorType(), "运营商不能为空");
        Assert.notNull(simCard.getNetworkMode(), "网络制式不能为空");
        Assert.notNull(simCard.getId(), "ID不能为空");
        Assert.isTrue(RegexUtils.isPhone(simCard.getPhoneNumber()), "不合法的手机号");

        ResultMsg<?> resultMsg = verifyPhoneNumber(simCard);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        simCardService.update(simCard);
        return ResultMsg.resultSuccess("SIM卡更新成功");
    }

    @ApiOperation(value = "删除SIM卡")
    @PostMapping("/delete/{id}")
    public ResultMsg<?> delete(@PathVariable("id") Long id) {
        SimCard simCard = simCardService.getEntityById(id);
        Assert.notNull(simCard, "指定的sim卡不存在");
        ControlCard cc = new ControlCard();
        cc.setSimCardId(id);
        long count = controlCardService.getCount(cc);
        Assert.isTrue(count == 0, "存在控制卡和该SIM卡绑定关系，请先解绑");
        Gateway gw = new Gateway();
        gw.setSimCardId(id);
        count = gatewayService.getCount(gw);
        Assert.isTrue(count == 0, "存在网关和该SIM卡绑定关系，请先解绑");

        simCardService.delete(id);
        return ResultMsg.resultSuccess("删除SIM卡成功");
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查找SIM卡列表", notes = "查询条件:手机号")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<Page<SimCard>> findPageList(@RequestBody SimCard simCard, @PathVariable int page, @PathVariable int rows) {
        Page<SimCard> rstPage = simCardService.findPageList(simCard, new PageRequest(page - 1, rows, Sort.Direction.DESC, "createTime"));
        return ResultMsg.resultSuccess(rstPage);
    }

    @LogMonitor(false)
    @ApiOperation(value = "手机号唯一性校验")
    @PostMapping("/verifyPhoneNumber")
    public ResultMsg<?> verifyPhoneNumber(@RequestBody SimCard simCard) {
        Assert.hasText(simCard.getPhoneNumber(), "手机号不能为空");
        SimCard card = new SimCard();
        card.setPhoneNumber(simCard.getPhoneNumber());
        List<SimCard> list = simCardService.findAll(card);
        return verifyForList(list, simCard, "手机号");
    }

    @LogMonitor(false)
    @ApiOperation(value = "查找SIM卡列表")
    @PostMapping("/list")
    public ResultMsg<List<SimCard>> findList() {
        List<SimCard> list = simCardService.findAll();
        return ResultMsg.resultSuccess(list);
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<SimCard> getEntityById(@PathVariable("id") Long id) {
        SimCard s = simCardService.getEntityById(id);
        return ResultMsg.resultSuccess(s);
    }
}
