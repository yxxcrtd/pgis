package com.igoosd.biz.util;

import com.igoosd.exception.StaticException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 获取TemplateService
 */
@Service
public class TemplateUtils {


    private static final Configuration configuration;

    static {
        URL url = TemplateUtils.class.getClassLoader().getResource("");
        File file = new File(url.getPath() + "/ftl");
        configuration = new Configuration();
        try {
            configuration.setDirectoryForTemplateLoading(file);

        } catch (IOException e) {
            e.printStackTrace();
            throw new StaticException("无法初始化模板信息");
        }
    }


    public static final Template getTemplate(String name) {
        try {
            return configuration.getTemplate(name);
        } catch (IOException e) {
            throw new StaticException("找不到指定的模板，name=" + name);
        }
    }

}
