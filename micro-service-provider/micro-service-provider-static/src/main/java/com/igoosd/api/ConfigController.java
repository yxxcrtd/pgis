package com.igoosd.api;

import com.igoosd.domain.Config;
import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.service.ConfigService;
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

import javax.servlet.http.HttpServletRequest;

import static com.igoosd.domain.vo.ResultMsg.resultFail;
import static com.igoosd.domain.vo.ResultMsg.resultSuccess;
import static com.igoosd.util.Constants.INT_MINUS_200;

/**
 * Config API
 */
@Api(tags = "配置管理")
@RestController
@RequestMapping(value = "api/config", produces = {"application/json;charset=UTF-8"})
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * 配置列表
     */
    @ApiOperation(value = "获取配置列表", httpMethod = "POST")
    @PostMapping("list/{page}/{rows}")
    ResultMsg list(@RequestBody Config config, @PathVariable int page, @PathVariable int rows) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page - 1, rows, sort);
        Page<Config> configPage = configService.findPageList(config, pageable);
        return resultSuccess("获取配置列表成功", configPage);
    }

    /**
     * findById
     */
    @ApiOperation(value = "findById", notes = "根据主键ID获取配置对象", httpMethod = "POST")
    @PostMapping("{id}")
    ResultMsg findById(@PathVariable Long id) {
        try {
            return resultSuccess("获取配置对象成功！", configService.getEntityById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return resultFail(INT_MINUS_200, "获取配置对象失败！,id=" + id);
        }
    }

    /**
     * findByCode
     */
    @ApiOperation(value = "findByCode", notes = "根据code获取对应的配置列表", httpMethod = "POST")
    @PostMapping(value = "findByCode/{code}", produces = {"application/json;charset=UTF-8"})
    ResultMsg findByCode(@PathVariable String code) {
        try {
            Config config = new Config();
            config.setName(code);
            return resultSuccess("根据code获取对应的配置列表成功！", configService.findAll(config));
        } catch (Exception e) {
            e.printStackTrace();
            return resultFail(INT_MINUS_200, "根据code获取对应的配置列表！,code=" + code);
        }
    }

    /**
     * 保存配置
     */
    @ApiOperation(value = "保存配置", notes = "保存配置对象", httpMethod = "POST")
    @PostMapping("save")
    ResultMsg save(@RequestBody Config config, HttpServletRequest request) {
        try {
            configService.insert(config);
            return resultSuccess("保存配置对象成功！", null);
        } catch (Exception e) {
            e.printStackTrace();
            return resultFail(INT_MINUS_200, "保存配置对象失败！");
        }
    }

    /**
     * 更新配置
     */
    @ApiOperation(value = "更新配置", notes = "更新配置对象", httpMethod = "POST")
    @PostMapping("update")
    ResultMsg update(@RequestBody Config config, HttpServletRequest request) {
        try {
            configService.update(config);
            return resultSuccess("更新配置对象成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return resultFail(INT_MINUS_200, "更新配置对象失败！");
        }
    }

    /**
     * 删除配置
     */
    @ApiOperation(value = "删除配置", notes = "根据主键ID删除配置", httpMethod = "POST")
    @PostMapping("delete/{id}")
    ResultMsg forbidden(@PathVariable Long id, HttpServletRequest request) {
        try {
            Config config = configService.getEntityById(id);
            if (null != config) {
                configService.delete(config);
                return resultSuccess("配置删除成功！", "id=" + id);
            } else {
                return resultFail(INT_MINUS_200, "配置删除失败！对象不存在！id=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultFail(INT_MINUS_200, "配置删除失败！, id=" + id);
        }
    }

}
