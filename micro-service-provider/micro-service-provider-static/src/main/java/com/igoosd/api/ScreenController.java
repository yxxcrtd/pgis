package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.Program;
import com.igoosd.domain.Screen;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.enumeration.ScreenTypeEnum;
import com.igoosd.service.ScreenService;
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

@Api(tags = "屏幕接口")
@RestController
@RequestMapping("/api/screen")
public class ScreenController extends BaseController<Screen> {

    @Autowired
    private ScreenService screenService;

    @ApiOperation(value = "新增屏幕", notes = "名称（必填）、编码（必填）、type(必填、下拉框，默认1车位数屏)、所属控制卡(必填、下拉框)、所属有诱导牌（必填、下拉框）、序号（数字、必填）、长度(必填、整数)、宽度(必填、整数)")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody Screen screen) {
        Assert.hasText(screen.getName(), "名称不能为空");
        Assert.hasText(screen.getCode(), "编码不能为空");
        Assert.notNull(screen.getType(), "类型不能为空");
        Assert.notNull(screen.getControlCardId(), "控制卡不能为空");
        Assert.notNull(screen.getInducePlateId(), "诱导牌不能为空");
        Assert.notNull(screen.getOrderNum(), "序号不能为空");
        ResultMsg<?> resultMsg = verifyCode(screen);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        resultMsg = verifyMediaScreen(screen);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        screen.setCreateTime(new Date());
        screenService.insert(screen);
        return ResultMsg.resultSuccess("新增屏幕成功");
    }

    @ApiOperation(value = "更新屏幕", notes = "id(必填、隐藏)名称（必填）、编码（必填）、type(必填、下拉框，默认1车位数屏)、所属控制卡(必填、下拉框)、所属有诱导牌（必填、下拉框）、长度(必填、整数)、宽度(必填、整数)、序号（数字、必填）")
    @PostMapping("/update")
    public ResultMsg<?> update(@RequestBody Screen screen) {
        Assert.hasText(screen.getName(), "名称不能为空");
        Assert.hasText(screen.getCode(), "编码不能为空");
        Assert.notNull(screen.getType(), "类型不能为空");
        Assert.notNull(screen.getControlCardId(), "控制卡不能为空");
        Assert.notNull(screen.getInducePlateId(), "诱导牌不能为空");
        Assert.notNull(screen.getId(), "ID不能为空");
        Assert.notNull(screen.getOrderNum(), "序号不能为空");
        ResultMsg<?> resultMsg = verifyCode(screen);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        resultMsg = verifyMediaScreen(screen);
        if (!resultMsg.checkSuccess()) {
            return resultMsg;
        }
        screenService.update(screen);
        return ResultMsg.resultSuccess("更新屏幕成功");
    }

    /**
     * 多媒体屏校验  所选控制卡不能被绑定到其他多媒体屏中
     */
    private ResultMsg<?> verifyMediaScreen(Screen screen){
        if(ScreenTypeEnum.MEDIA.getValue() == screen.getType()){
            Screen temp = new Screen();
            temp.setControlCardId(screen.getControlCardId());
            List<Screen> list = screenService.findAll(temp);
            return verifyForList(list,screen,"文字信息屏下的控制卡");
        }
        return ResultMsg.resultSuccess();
    }

    @LogMonitor(false)
    @ApiOperation(value = "编码唯一性校验")
    @PostMapping("/verifyCode")
    public ResultMsg<?> verifyCode(@RequestBody Screen screen) {
        Assert.hasText(screen.getCode(), "编码不能为空");
        Screen temp = new Screen();
        temp.setCode(screen.getCode());
        List<Screen> list = screenService.findAll(temp);
        return verifyCodeForList(list, screen);
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查询屏幕列表", notes = "查询条件:名称、编码、控制卡(下拉框)、诱导牌(下拉框)、类型(下拉框)")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<Page<Screen>> findPageList(@RequestBody Screen screen, @PathVariable int page, @PathVariable int rows) {
        Page<Screen> rstPage = screenService.findPageList(screen, new PageRequest(page - 1, rows, Sort.Direction.DESC, "createTime"));
        return ResultMsg.resultSuccess(rstPage);
    }

    @LogMonitor(false)
    @ApiOperation(value = "查找不同类型的屏幕列表", notes = "type : 1车位数屏 2：文字信息屏")
    @PostMapping("/list/{type}")
    public ResultMsg<List<Screen>> findScreenList(@PathVariable int type) {

        Screen screen = new Screen();
        screen.setType(type);
        List<Screen> list = screenService.findAll(screen);
        return ResultMsg.resultSuccess(list);
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<Screen> getEntityById(@PathVariable("id") Long id) {
        Screen s = screenService.getEntityById(id);
        return ResultMsg.resultSuccess(s);
    }



}
