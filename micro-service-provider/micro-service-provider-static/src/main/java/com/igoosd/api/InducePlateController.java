package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.InducePlate;
import com.igoosd.domain.Parking;
import com.igoosd.domain.Program;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.service.InducePlateService;
import com.igoosd.service.ParkingService;
import com.igoosd.service.ProgramService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "诱导牌接口")
@RestController
@RequestMapping("/api/inducePlate")
public class InducePlateController extends BaseController<InducePlate> {


    @Autowired
    private InducePlateService inducePlateService;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ProgramService programService;

    @ApiOperation(value = "新增诱导牌", notes = "名称（必填）、编码（必填）、等级（必填、下拉框）、位置、描述")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody InducePlate inducePlate) {
        Assert.hasText(inducePlate.getName(), "名称不能为空");
        Assert.notNull(inducePlate.getLevel(), "等级不能为空");

        ResultMsg<?> resultMsg = verifyCode(inducePlate);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }

        inducePlate.setCreateTime(new Date());
        inducePlateService.insert(inducePlate);
        return ResultMsg.resultSuccess("新增诱导牌成功");
    }

    @ApiOperation(value = "更新诱导牌", notes = "id(主键、隐藏)、名称（必填）、编码（必填）、等级（必填、下拉框）、位置、描述")
    @PostMapping("/update")
    public ResultMsg<?> update(@RequestBody InducePlate inducePlate) {
        Assert.hasText(inducePlate.getName(), "名称不能为空");
        Assert.notNull(inducePlate.getLevel(), "等级不能为空");
        Assert.notNull(inducePlate.getId(), "ID不能为空");
        ResultMsg<?> resultMsg = verifyCode(inducePlate);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        inducePlateService.update(inducePlate);
        return ResultMsg.resultSuccess("更新诱导牌成功");
    }

    @ApiOperation(value = "编码唯一性验证")
    @PostMapping("/verifyCode")
    public ResultMsg<?> verifyCode(@RequestBody InducePlate inducePlate) {
        Assert.hasText(inducePlate.getCode(), "编码不能为空");
        InducePlate temp = new InducePlate();
        temp.setCode(inducePlate.getCode());
        List<InducePlate> list = inducePlateService.findAll(temp);
        return verifyCodeForList(list, inducePlate);
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查询诱导牌列表", notes = "查询条件:名称、编码、等级")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<Page<InducePlate>> findPageList(@RequestBody InducePlate inducePlate, @PathVariable int page, @PathVariable int rows) {
        Page<InducePlate> rstPage = inducePlateService.findPageList(inducePlate, new PageRequest(page - 1, rows, Sort.Direction.DESC, "createTime"));
        return ResultMsg.resultSuccess(rstPage);
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<InducePlate> getEntityById(@PathVariable("id") Long id) {
        InducePlate ip = inducePlateService.getEntityById(id);
        return ResultMsg.resultSuccess(ip);
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查询诱导牌列表", notes = "查询条件:名称、编码、等级")
    @PostMapping("/list")
    public ResultMsg<List<InducePlate>> findAll() {
        List<InducePlate> list = inducePlateService.findAll();
        return ResultMsg.resultSuccess(list);
    }


    @LogMonitor(false)
    @ApiOperation(value = "根据诱导牌ID查找绑定的停车场列表",notes = "data中包含 parkingList 和programList 两个数组")
    @PostMapping("/info/{id}")
    public ResultMsg<Map<String,Object>> findParkingList(@PathVariable("id") Long id){
        List<Parking> parkingList = parkingService.getParkingListByInducePlateId(id);
        List<Program> programList = programService.getLatestProgramListByInducePlateId(id);
        Map<String,Object> rstMap = new HashMap<>();
        rstMap.put("parkingList",parkingList);
        rstMap.put("programList",programList);
        return ResultMsg.resultSuccess(rstMap);
    }

}
