package com.igoosd.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.igoosd.biz.ScreenInitProperties;
import com.igoosd.biz.ScreenSendService;
import com.igoosd.domain.Config;
import com.igoosd.domain.ControlCard;
import com.igoosd.domain.Program;
import com.igoosd.domain.ProgramScreen;
import com.igoosd.domain.Screen;
import com.igoosd.enumeration.ProgramStatusEnum;
import com.igoosd.enumeration.ScreenTypeEnum;
import com.igoosd.repository.ProgramRepository;
import com.igoosd.repository.dao.ProgramDao;
import com.igoosd.service.ConfigService;
import com.igoosd.service.ControlCardService;
import com.igoosd.service.ProgramScreenService;
import com.igoosd.service.ProgramService;
import com.igoosd.service.ScreenService;
import com.igoosd.util.AbsCommonService;
import com.igoosd.util.Assert;
import com.igoosd.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 内容管理接口实现
 */
@Service
@Slf4j
public class ProgramServiceImpl extends AbsCommonService<Program, Long> implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private ProgramScreenService programScreenService;
    @Autowired
    private ScreenSendService screenSendService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private ControlCardService controlCardService;
    @Autowired
    private ScreenInitProperties screenInitProperties;
    @Autowired
    private ProgramDao programDao;
    @Autowired
    private ConfigService configService;

    @Override
    public Page<Program> findPageList(Program program, Pageable pageable) {
        return programDao.findPage(program, pageable);
    }

    @Override
    protected JpaRepository<Program, Long> getRepository() {
        return this.programRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void succeedVerify(Program program) {
        Program oldProgram = this.getEntityById(program.getId());
        Assert.notNull(oldProgram, "不合法的节目ID");
        Assert.isTrue(ProgramStatusEnum.WAITING_VERIFY.getValue() == oldProgram.getStatus(), "非待审核状态节目不允许审核操作");
        if (ProgramStatusEnum.RELEASE_SUCCESS.getValue() == program.getStatus()) {
            try {
                //节目 屏幕发送
                ProgramScreen tempPs = new ProgramScreen();
                tempPs.setProgramId(program.getId());
                List<ProgramScreen> psList = programScreenService.findAll(tempPs);
                Screen screen = screenService.getEntityById(psList.get(0).getScreenId());
                ControlCard controlCard = controlCardService.getEntityById(screen.getControlCardId());
                String url = screenInitProperties.getDomain() + "content/mediaPage?width="
                        + screen.getWidth() + "&height=" + screen.getHeight() + "&programId=" + program.getId();
                screenSendService.loadUrlForScreen(controlCard.getCode(), url);

                oldProgram.setReleaseTime(new Date());
                oldProgram.setStatus(ProgramStatusEnum.RELEASE_SUCCESS.getValue());
            } catch (Exception e) {
                log.error("发布屏幕数据异常", e);
                oldProgram.setStatus(ProgramStatusEnum.RELEASE_FAIL.getValue());
            }
        } else {
            oldProgram.setStatus(ProgramStatusEnum.VERIFY_FAIL.getValue());
        }

        oldProgram.setUpdateTime(new Date());
        oldProgram.setRemark(program.getRemark());
        programRepository.saveAndFlush(oldProgram);
    }

    @Override
    public Program getLatestReleaseProgram(Long screenId) {
        ProgramScreen temp = new ProgramScreen();
        temp.setScreenId(screenId);
        List<ProgramScreen> pss = programScreenService.findAll(temp);
        if (CollectionUtils.isEmpty(pss)) {
            return null;
        }
        List<Long> ids = new ArrayList<>(pss.size());
        for (ProgramScreen ps : pss) {
            ids.add(ps.getProgramId());
        }
        Program program = programRepository.getLatestReleaseProgram(ids);
        return program;
    }

    @Override
    public List<Program> getLatestProgramListByInducePlateId(Long inducePlateId) {
        Screen temp = new Screen();
        temp.setType(ScreenTypeEnum.MEDIA.getValue());
        temp.setInducePlateId(inducePlateId);
        List<Screen> mediaScreenList = screenService.findAll(temp);
        List<Program> programList;
        if (!CollectionUtils.isEmpty(mediaScreenList)) {
            programList = new ArrayList<>(mediaScreenList.size());
            for (Screen screen : mediaScreenList) {
                Program program = getLatestReleaseProgram(screen.getId());
                if (null == program) {
                    String cfgKey = Constants.DEFAULT_PROGRAM_CONTENT_PRE_CONFIG_KEY + screen.getWidth()
                            + "*" + screen.getHeight();
                    Config config = configService.getByName(cfgKey);
                    program = new Program();
                    String contentItem = config!=null ? config.getValue() :"<p style=\"text-align: center;\">\n" +
                            "    <span style=\"font-size: 14px; color: rgb(0, 176, 80);\"><strong>欢迎使用谷声科技产品</strong></span><br/>\n" +
                            "</p>";
                    String[] str = {contentItem};
                    program.setShowType(0);//静态显示
                    program.setContent(JSONArray.toJSONString(str));
                }
                programList.add(program);
            }
        } else {
            programList = Collections.EMPTY_LIST;
        }
        return programList;
    }

}
