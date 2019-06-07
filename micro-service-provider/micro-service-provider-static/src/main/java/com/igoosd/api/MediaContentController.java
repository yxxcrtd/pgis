package com.igoosd.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.igoosd.biz.util.TemplateUtils;
import com.igoosd.domain.Config;
import com.igoosd.domain.Program;
import com.igoosd.domain.vo.LotScreenVo;
import com.igoosd.domain.vo.ProgramVo;
import com.igoosd.service.ConfigService;
import com.igoosd.service.ProgramService;
import com.igoosd.util.Constants;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("content")
public class MediaContentController {


    @Autowired
    private ProgramService programService;
    @Autowired
    private ConfigService configService;


    @GetMapping("/lotPage")
    public void getLotPage(HttpServletResponse response, @RequestParam String ids, @RequestParam String carNumbers, @RequestParam Integer warnNum) throws IOException, TemplateException {
        String[] idArray = ids.split(",");
        String[] lotRemainArray = carNumbers.split(",");
        List<LotScreenVo> list = new ArrayList<>(idArray.length);
        for (int i = 0; i < idArray.length; i++) {
            LotScreenVo vo = new LotScreenVo();
            int lotRemainCount = Integer.parseInt(lotRemainArray[i]);
            vo.setId(idArray[i]);
            vo.setLotRemainCount(String.format("%03d", lotRemainCount));
            if (lotRemainCount == 0) {
                vo.setColor("red");
            } else if (lotRemainCount <= warnNum) {
                vo.setColor("yellow");
            } else {
                vo.setColor("green");
            }
            list.add(vo);
        }
        //响应
        Template template = TemplateUtils.getTemplate("LotPage.ftl");
        Map<String, Object> params = new HashMap<>();
        params.put("parkingList", list);
        params.put("warnNum", warnNum);

        PrintWriter writer = response.getWriter();
        template.process(params, writer);
        writer.flush();
    }


    @GetMapping("/mediaPage")
    public void getContentPage(HttpServletResponse response, @RequestParam int width, @RequestParam int height, @RequestParam(required = false) Long programId) throws IOException, TemplateException {

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ProgramVo pvo = new ProgramVo();
        if (null != programId) {
            Program program = programService.getEntityById(programId);
            pvo.setInterval(program.getInterval());
            pvo.setStep(program.getStep());
            pvo.setContent(JSON.parseArray(program.getContent(), String.class));
            pvo.setShowType(program.getShowType());
        } else {
            String keyName = Constants.DEFAULT_PROGRAM_CONTENT_PRE_CONFIG_KEY + width + "*" + height;
            Config config = configService.getByName(keyName);
            String item = config != null ? config.getValue() :
                    "<p>\n" +
                            "    <span style=\"color: rgb(255, 0, 0);\">北京天坛医院欢迎你！</span><br/>\n" +
                            "</p>";
            //配置化 program 内容
            List<String> list = new ArrayList<>(1);
            list.add(item);
            pvo.setContent(list);
        }
        //响应
        Template template = TemplateUtils.getTemplate("ContentPage.ftl");
        Map<String, String> map = new HashMap<>();
        map.put("height", height + "");
        map.put("width", width + "");
        map.put("program", JSON.toJSONString(pvo));

        PrintWriter writer = response.getWriter();
        template.process(map, writer);
        writer.flush();
    }


    @GetMapping("/zero")
    public void initZeroPage(@RequestParam String unitName,@RequestParam int width,@RequestParam int height, HttpServletResponse response) throws IOException, TemplateException {
        Config config = configService.getByName(Constants.CONFIG_ZERO_CAR_NUM_MEDIA_INFO);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (config == null) {
            return;
        } else {
            String text = config.getValue();
            text = text.replace("${unitName}", unitName);
            Map<String, Object> paramMap = new HashMap<>(1);
            JSONObject jo = new JSONObject();
            jo.put("data", text);
            paramMap.put("content", jo.toJSONString());
            paramMap.put("width",width);
            paramMap.put("height",height);
            Template template = TemplateUtils.getTemplate("ZeroCarNumContentPage.ftl");
            PrintWriter writer = response.getWriter();
            template.process(paramMap, writer);
            writer.flush();
        }

    }

}
