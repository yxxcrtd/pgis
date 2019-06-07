package com.igoosd.api;

import com.igoosd.config.LogMonitor;
import com.igoosd.domain.Log;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.service.LogService;
import com.igoosd.util.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.igoosd.domain.vo.ResultMsg.resultFail;
import static com.igoosd.domain.vo.ResultMsg.resultSuccess;
import static com.igoosd.util.Constants.INT_MINUS_200;

/**
 * Log API
 */
@Api(tags = "业务日志")
@RestController
@RequestMapping(value = "/api/log", produces = {"application/json;charset=UTF-8"})
public class LogController {

    @Autowired
    private LogService logService;

    private CommonService getAbsService() {
        return logService;
    }

    /**
     * 日志列表
     */
    @LogMonitor(false)
    @ApiOperation(value = "获取日志列表", httpMethod = "POST")
    @PostMapping("list/{page}/{rows}")
    ResultMsg list(@RequestBody Log log, @PathVariable int page, @PathVariable int rows) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page - 1, rows, sort);
        Page<Log> dictPage = logService.findPageList(log, pageable);
        return resultSuccess("获取日志列表成功", dictPage);
    }

    /**
     * findById
     */
    @LogMonitor(false)
    @ApiOperation(value = "findById", notes = "根据主键ID获取日志对象", httpMethod = "POST")
    @PostMapping("{id}")
    ResultMsg findById(@PathVariable Long id) {
        try {
            return resultSuccess("获取日志对象成功！", logService.getEntityById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return resultFail(INT_MINUS_200, "获取日志对象失败！,id=" + id);
        }
    }

}
