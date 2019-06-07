package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.Program;
import com.igoosd.domain.ProgramScreen;
import com.igoosd.domain.User;
import com.igoosd.domain.vo.ProgramVerifyVo;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.enumeration.ProgramStatusEnum;
import com.igoosd.service.ProgramScreenService;
import com.igoosd.service.ProgramService;
import com.igoosd.util.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 节目接口
 */
@Api(tags = "节目接口")
@RestController
@RequestMapping("/api/program")
public class ProgramController extends BaseController<Program> {

    @Autowired
    private ProgramService programService;

    @Autowired
    private ProgramScreenService programScreenService;

    @LogMonitor(false)
    @ApiOperation(value = "新增节目", notes = "名称（必填）、内容、是否滚动（默认滚动 true）、颜色（默认 红色（red、green、yellow））、滚动间隔（默认50ms）、滚动速率（默认1 像素）、字体大小、描述")
    @PostMapping("/save")
    public ResultMsg<?> save(@RequestBody Program program, HttpServletRequest request) {
        Assert.hasText(program.getName(), "名称不能为空");
        Assert.hasText(program.getContent(), "内容不能为空");
        User user = getUserFromSession(request);
        program.setUserId(user.getId());
        program.setStatus(ProgramStatusEnum.DRAFT.getValue());
        program.setCreateTime(new Date());
        program.setUpdateTime(new Date());

        programService.insert(program);
        return ResultMsg.resultSuccess("新增节目成功");
    }


    @LogMonitor(false)
    @ApiOperation(value = "更新节目", notes = "id(主键、隐藏)、名称（必填）、内容、是否滚动（默认滚动 true）、颜色（默认 红色（red、green、yellow））、滚动间隔（默认50ms）、滚动速率（默认1 像素）、字体大小、描述")
    @PostMapping("/update")
    public ResultMsg<?> update(@RequestBody Program program) {
        Assert.hasText(program.getName(), "名称不能为空");
        Assert.hasText(program.getContent(), "内容不能为空");
        Assert.notNull(program.getId(), "ID不能为空");
        Program old = programService.getEntityById(program.getId());
        Assert.isTrue(ProgramStatusEnum.DRAFT.getValue() == old.getStatus(), "非草稿状态的节目不允许修改");
        programService.update(program);
        return ResultMsg.resultSuccess("更新节目成功");
    }

    @LogMonitor(false)
    @ApiOperation(value = "分页查询节目列表", notes = "查询条件:名称")
    @PostMapping("/list/{page}/{rows}")
    public ResultMsg<?> findPageList(@RequestBody Program program, @PathVariable int page, @PathVariable int rows) {
        Page<Program> rstPage = programService.findPageList(program, new PageRequest(page - 1, rows, Sort.Direction.DESC, "updateTime"));
        return ResultMsg.resultSuccess(rstPage);
    }

    @ApiOperation(value = "绑定屏幕")
    @PostMapping("/bind/{programId}")
    public ResultMsg<?> bindScreens(@PathVariable Long programId, @RequestBody List<Long> screenIds) {
        Program program = programService.getEntityById(programId);
        Assert.notNull(program, "不合法的节目ID");
        Assert.isTrue(ProgramStatusEnum.DRAFT.getValue() == program.getStatus(), "非草稿状态下的节目不允许绑定屏");

        programScreenService.bindScreen(programId, screenIds);
        return ResultMsg.resultSuccess("绑定成功");
    }

    @ApiOperation(value = "送审")
    @PostMapping("/submitVerify/{id}")
    public ResultMsg<?> submitVerify(@PathVariable Long id) {
        Program program = programService.getEntityById(id);
        Assert.notNull(program, "不合法的节目ID");
        Assert.isTrue(ProgramStatusEnum.DRAFT.getValue() == program.getStatus(), "非草稿状态下的节目不允许送审");

        //是否关联屏
        ProgramScreen ps = new ProgramScreen();
        ps.setProgramId(id);
        long count = programScreenService.getCount(ps);
        Assert.isTrue(count > 0, "节目未绑定相关屏，送审失败");
        program.setStatus(ProgramStatusEnum.WAITING_VERIFY.getValue());
        programService.update(program);
        return ResultMsg.resultSuccess("送审成功");
    }

    @ApiOperation(value = "审核通过并发布")
    @PostMapping("/succeedVerify")
    public ResultMsg<?> succeedVerify(@RequestBody ProgramVerifyVo pvo) {
        Assert.notNull(pvo.getId(), "id不能为空");
        Assert.notNull(pvo.isSuccess(), "审核结果不能为空");
        Assert.isTrue(pvo.isSuccess() ? true : !StringUtils.isEmpty(pvo.getRemark()), "审核失败 描述不能为空");

        Program program = new Program();
        program.setId(pvo.getId());
        program.setRemark(pvo.getRemark());
        program.setStatus(pvo.isSuccess() ? ProgramStatusEnum.RELEASE_SUCCESS.getValue() : ProgramStatusEnum.VERIFY_FAIL.getValue());

        programService.succeedVerify(program);
        return ResultMsg.resultSuccess("审核完成");
    }

    @LogMonitor(false)
    @ApiOperation(value = "根据Id查询实体信息")
    @PostMapping("/{id}")
    public ResultMsg<Program> getEntityById(@PathVariable("id") Long id) {
        Program p = programService.getEntityById(id);
        return ResultMsg.resultSuccess(p);
    }


    @ApiOperation(value = "根据Id查询绑定的最新节目")
    @PostMapping("latestProgram/{screenId}")
    public ResultMsg<Program> getProgramByScreenId(@PathVariable("screenId") Long screenId){
        Program program = programService.getLatestReleaseProgram(screenId);
        return ResultMsg.resultSuccess(program);
    }
}
