package com.igoosd.config;

import com.igoosd.domain.Log;
import com.igoosd.domain.User;
import com.igoosd.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.igoosd.util.Constants.SESSION_USER_KEY;
import static com.igoosd.util.WebUtils.getIP;

@Aspect
@Order(5)
@Slf4j
@Component
public class WebLogAspect {

    @Autowired
    private LogService logService;

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private long id;

    @Pointcut("within(com.igoosd.api..*)")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        LogMonitor monitor =((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(LogMonitor.class);

        if(null != monitor && !monitor.value()){
            startTime.set(null);
            return;
        }
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Log log = new Log();
        String url = request.getRequestURL().toString();
        log.setUrl(url);
        WebLogAspect.log.info("URL : " + url);

        String httpMethod = request.getMethod();
        log.setHttpMethod(httpMethod);
        WebLogAspect.log.info("HTTP_METHOD : " + httpMethod);

        String ip = getIP(request);
        log.setIp(ip);
        WebLogAspect.log.info("IP : " + getIP(request));

        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.setClassMethod(classMethod);
        WebLogAspect.log.info("CLASS_METHOD : " + classMethod);

        String args = Arrays.toString(joinPoint.getArgs());
        log.setArgs(args);
        WebLogAspect.log.info("ARGS : " + args);

        User user = (User) request.getSession().getAttribute(SESSION_USER_KEY);
        if (null != user) {
            log.setUsername(user.getUsername());
        }
        logService.insert(log);
        id = log.getId();
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        if(startTime.get() == null){
            return;
        }
        Log log = logService.getEntityById(id);
        String response = "";
        if (null != ret) {
            response = ret.toString();
        }
        log.setResponse(response);
        WebLogAspect.log.info("RESPONSE : " + ret);

        long spend = (System.currentTimeMillis() - startTime.get());
        log.setSpendTime(spend);
        WebLogAspect.log.info("SPEND TIME : " + spend);

        logService.update(log);
    }

}
