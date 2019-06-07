package com.igoosd.api;

import com.igoosd.biz.AcceptorService;
import com.igoosd.config.LogMonitor;
import com.igoosd.domain.Parking;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.service.ParkingScreenService;
import com.igoosd.service.ParkingService;
import com.igoosd.util.Assert;
import com.igoosd.util.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.igoosd.domain.vo.ResultMsg.resultFail;
import static com.igoosd.domain.vo.ResultMsg.resultSuccess;

/**
 * 停车场接口
 */
@Slf4j
@Api(tags = "停车场相关接口")
@RestController
@RequestMapping("/api/parking")
public class ParkingController extends BaseController<Parking> {

    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingScreenService parkingScreenService;
    @Autowired
    private AcceptorService acceptorService;

    @ApiOperation(value = "新增停车场", notes = "名称（必填）、编码（必填）、类型（必填、下拉框）、接入方式（必填、下拉框）、车位数（大于0 整数）、剩余车位数（大于等于0 整数）")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody Parking parking) {
        Assert.hasText(parking.getName(), "名称不能为空");
        Assert.notNull(parking.getType(), "类型不能为空");
        Assert.notNull(parking.getAccessMode(), "接入方式不能为空");
        Assert.notNull(parking.getLotTotalCount(), "车位数不能为空");
        Assert.notNull(parking.getLotRemainCount(), "剩余车位数不能为空");
        Assert.isTrue(parking.getLotTotalCount() > 0, "车位数必须大于0");
        Assert.isTrue(parking.getLotRemainCount() >= 0, "剩余车位数必须不小于0");
        Assert.isTrue(parking.getLotTotalCount() >= parking.getLotRemainCount(), "车位数必须不小于剩余车位数");
        //编码唯一性校验
        ResultMsg resultMsg = verifyCode(parking);
        if(!resultMsg.checkSuccess()){
            return resultMsg;
        }
        parking.setCreateTime(new Date());
        parkingService.insert(parking);
        return resultSuccess("新增停车场成功");
    }

    private @Value("${key.auth_id}") String authId;
    private @Value("${key.auth_key}") String authKey;

    @ApiOperation("remain")
    @PostMapping("remain")
    ResultMsg remain(@RequestParam String key, @RequestParam Long pid, @RequestParam int count) {
        log.info("接收到的key：{}", key);
        log.info("接收到的pid：{}", pid);
        log.info("接收到的数值：{}", count);

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String localKey = MD5.toMD5(authId + authKey + sdf.format(new Date()));
        log.info("本地生成的key：{}", localKey);
        if (!key.endsWith(localKey)) {
            return resultFail(501, "授权失败");
        }

        if (0 > count) {
            return resultFail(503, "剩余车位数有误！");
        }

        if (999 < count) {
            return resultFail(504, "剩余车位数不能超过1000！");
        }

        Parking parking = parkingService.getEntityById(pid);
        if (null != parking) {
            parking.setLotRemainCount(count);
            parkingService.update(parking);
        } else {
            return resultFail(502, "停车场不存在");
        }

        new Thread(() -> acceptorService.sendLotRemainCount(parking.getId())).start();
        return resultSuccess("发送成功");
    }


    @ApiOperation(value = "新增停车场", notes = "ID(必填、隐藏域)、名称（必填）、编码（必填）、类型（必填、下拉框）、接入方式（必填、下拉框）、车位数（大于0 整数）、剩余车位数（大于等于0 整数）")
    @PostMapping("/update")
    public ResultMsg<?> update(@RequestBody Parking ps) {
        Assert.hasText(ps.getName(), "名称不能为空");
        Assert.notNull(ps.getType(), "类型不能为空");
        Assert.notNull(ps.getAccessMode(), "接入方式不能为空");
        Assert.notNull(ps.getLotTotalCount(), "车位数不能为空");
        Assert.notNull(ps.getLotRemainCount(), "剩余车位数不能为空");
        Assert.isTrue(ps.getLotTotalCount() > 0, "车位数必须大于0");
        Assert.isTrue(ps.getLotRemainCount() >= 0, "剩余车位数必须不小于0");
        Assert.isTrue(ps.getLotTotalCount() >= ps.getLotRemainCount(), "车位数必须不小于剩余车位数");
        Assert.notNull(ps.getId(), "id不能为空");
        //编码唯一性校验
        ResultMsg resultMsg = verifyCode(ps);
        if(!resultMsg.checkSuccess()){
            return resultMsg;
        }
        parkingService.update(ps);
        //发送车位数到屏幕中去
        new Thread(() -> acceptorService.sendLotRemainCount(ps.getId())).start();
        return resultSuccess("更新停车场成功");
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查询", notes = "查询条件: 名称、编码")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<Page<Parking>> findPageList(@PathVariable int page, @PathVariable int rows, @RequestBody Parking ps) {
        Page<Parking> rstPage = parkingService.findPageList(ps, new PageRequest(page - 1, rows, Sort.Direction.DESC, "createTime"));
        return resultSuccess(rstPage);
    }


    @LogMonitor(false)
    @ApiOperation(value = "编码唯一性校验", notes = "编码、id")
    @PostMapping("/verifyCode")
    public ResultMsg<?> verifyCode(@RequestBody Parking ps) {
        Assert.hasText(ps.getCode(), "编码不能为空");
        Parking temp = new Parking();
        temp.setCode(ps.getCode());
        List<Parking> list = parkingService.findAll(temp);
        return verifyCodeForList(list, ps);
    }

    @ApiOperation(value = "绑定屏幕")
    @PostMapping("/bind/{parkingId}")
    public ResultMsg<?> bindScreens(@PathVariable Long parkingId, @RequestBody List<Long> screenIds) {
        parkingScreenService.bindScreen(parkingId, screenIds);
        return resultSuccess("绑定成功");
    }

    @LogMonitor(false)
    @ApiOperation(value = "查找停车场列表")
    @PostMapping("/list")
    public ResultMsg<List<Parking>> findList() {
        List<Parking> list = parkingService.findAll();
        return resultSuccess(list);
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<Parking> getEntityById(@PathVariable("id") Long id) {
        Parking p = parkingService.getEntityById(id);
        return resultSuccess(p);
    }
}
