package com.igoosd.service;

import com.igoosd.domain.Program;
import com.igoosd.util.CommonService;

import java.util.List;

/**
 * 节目接口
 */
public interface ProgramService extends CommonService<Program, Long> {



    void succeedVerify(Program program);

    /**
     * 获取屏最新的发布节目
     * @param screenId
     * @return
     */
    Program getLatestReleaseProgram(Long screenId);


    /**
     * 查找诱导牌上文字信息屏上的最新节目列表
     * @param inducePlateId
     * @return
     */
    List<Program> getLatestProgramListByInducePlateId(Long inducePlateId);

}
