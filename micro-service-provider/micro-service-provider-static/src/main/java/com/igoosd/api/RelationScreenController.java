package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.ParkingScreen;
import com.igoosd.domain.ProgramScreen;
import com.igoosd.domain.Screen;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.service.ParkingScreenService;
import com.igoosd.service.ProgramScreenService;
import com.igoosd.service.ScreenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "屏幕关联接口")
@RestController
@RequestMapping("/api/relationScreen")
public class RelationScreenController {


    @Autowired
    private ParkingScreenService parkingScreenService;
    @Autowired
    private ProgramScreenService programScreenService;
    @Autowired
    private ScreenService screenService;

    @LogMonitor(false)
    @ApiOperation(value = "查找停车场关联屏幕ids列表")
    @PostMapping("/parking/list/{parkingId}")
    public ResultMsg<List<Screen>> findRelationForParking(@PathVariable Long parkingId) {
        ParkingScreen parkingScreen = new ParkingScreen();
        parkingScreen.setParkingId(parkingId);
        List<ParkingScreen> list = parkingScreenService.findAll(parkingScreen);
        List<Long> ids = new ArrayList<>(list.size());
        List<Screen> rstList = new ArrayList<>(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            for (ParkingScreen ps : list) {
                ids.add(ps.getScreenId());
            }
            rstList = screenService.findAll(ids);
        }
        return ResultMsg.resultSuccess(rstList);
    }

    @LogMonitor(false)
    @ApiOperation(value = "查找节目关联屏幕ids列表")
    @PostMapping("/program/list/{programId}")
    public ResultMsg<List<Screen>> findRelationForProgram(@PathVariable Long programId) {
        ProgramScreen programScreen = new ProgramScreen();
        programScreen.setProgramId(programId);
        List<ProgramScreen> list = programScreenService.findAll(programScreen);
        List<Long> ids = new ArrayList<>(list.size());
        List<Screen> rstList = new ArrayList<>(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            for (ProgramScreen ps : list) {
                ids.add(ps.getScreenId());
            }
            rstList = screenService.findAll(ids);
        }
        return ResultMsg.resultSuccess(rstList);
    }

}
