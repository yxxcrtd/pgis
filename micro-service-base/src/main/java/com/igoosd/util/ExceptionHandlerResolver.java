package com.igoosd.util;

import com.igoosd.domain.vo.ResultMsg;
import com.igoosd.exception.StaticException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@Component
public class ExceptionHandlerResolver extends ExceptionHandlerExceptionResolver {


    /**
     * 自定义异常显示异常映射处理  key="异常类全名" value="msg"
     */
    private Properties excpMappings = new Properties();

    /**
     * json消息转换器
     **/
    private HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();


    public void setMessageConverter(HttpMessageConverter messageConverter) {
        if (null != messageConverter) {
            this.messageConverter = messageConverter;
        }
    }

    public void setExcpMappings(Properties excpMappings) {
        this.excpMappings = excpMappings;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mav = new ModelAndView();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

        ResultMsg<?> resultVo = genResultMsg(ex);
        try {
            messageConverter.write(resultVo, MediaType.APPLICATION_JSON, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mav;
    }

    /**
     * 查找匹配的key-val 在exceptionMappings;
     *
     * @return
     */
    private <T> String findMatchPropVal(Class<T> clazz) {
        String key = clazz.getName();
        if (Exception.class.getName().equals(key) || RuntimeException.class.getName().equals(key)) {
            return null;
        }
        String val = excpMappings.getProperty(key);
        if (StringUtils.isEmpty(val)) {
            val = findMatchPropVal(clazz.getSuperclass());
        }
        return val;
    }

    /**
     * 生成响应需要的 resultVo
     *
     * @return
     */
    private ResultMsg<?> genResultMsg(Exception ex) {
        //med类及子类异常处理
        if (StaticException.class.isAssignableFrom(ex.getClass())) {
            StaticException exp = (StaticException) ex;
            log.error("业务异常:{}",exp.getMessage());
            return ResultMsg.resultFail(exp.getMessage());
        }
        if (!CollectionUtils.isEmpty(excpMappings)) {
            String val = findMatchPropVal(ex.getClass());
            if (!StringUtils.isEmpty(val)) {
                log.error("业务异常:{}",val);
                return ResultMsg.resultFail(val);
            }
        }
        log.error("系统异常:",ex);
        return ResultMsg.resultFail("系统异常");
    }

}