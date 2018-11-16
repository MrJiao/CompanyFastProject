package com.bjhy.fast.build.core.log;

import com.bjhy.fast.build.core.domain.ControllerLog;
import com.bjhy.fast.build.core.service.ControllerLogService;
import com.bjhy.fast.build.security.UserDetailsUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author welldo
 * @version 2018年5月21日 上午9:32:46
 */
@Aspect
@Component
public class LogAop {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ControllerLogService controllerLogService;
    ObjectMapper mapper = new ObjectMapper();

    /**
     * 返回通知
     */
    @AfterReturning(value = "logPointCut()", returning = "returnData")
    public void logReturn(JoinPoint joinPoint, Object returnData) {
        ControllerLog controllerLog = create(joinPoint, "成功");
        if(controllerLog==null)return;
        controllerLogService.save(controllerLog);
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        ControllerLog controllerLog = create(joinPoint, "失败");
        if(controllerLog==null)return;
        controllerLogService.save(controllerLog);
    }

    private ControllerLog create(JoinPoint joinPoint,String success){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        if(request==null)return null;

        LogIgnore logIgnore = LogAnnParser.parseLogIgnore(joinPoint);
        if (logIgnore!=null)return null;

        ControllerLog log = new ControllerLog();
        log.setSuccess(success);
        //log.setMethodName(joinPoint.getSignature().getName());
        log.setUsername(UserDetailsUtil.getCurrentUserName());
        log.setOperateTime(new Date());

        setMethodName(joinPoint,log);
        setParam(joinPoint,log);
        setIpAndUrl(request,log);
        return log;
    }

    private void setIpAndUrl(HttpServletRequest request, ControllerLog log) {
        log.setUrl(request.getRequestURL().toString());
        log.setIp(request.getRemoteAddr());
    }

    private void setParam(JoinPoint joinPoint, ControllerLog log) {
        Object[] args = joinPoint.getArgs();
        if(args==null || args.length==0)return;
        StringBuilder sb = new StringBuilder();
        for (Object o:args){
            try {
               String s = mapper.writeValueAsString(o);
               if(!StringUtils.isEmpty(s)){
                   sb.append(s);
                   sb.append(",");
               }
            } catch (JsonProcessingException e) {
                logger.error("解析json失败 Object:{} msg:{}",o.getClass().getName(),e.getMessage());
            }
        }
        if(sb.length()>0){
            sb.setLength(sb.length()-1);
            log.setParam(sb.toString());
        }
    }

    private void setMethodName(JoinPoint joinPoint, ControllerLog log) {
        LogDescribe logDescribe = LogAnnParser.parseLogDescribe(joinPoint);
        if(logDescribe!=null){
            log.setMethodName(logDescribe.value());
        }
    }

    /**
     * 此空方法, 用于定义切入点表达式
     */
    @Pointcut("execution(* com.bjhy.tlevel.datax.web..*Controller.*(..)) "
            + "&& execution(@org.springframework.web.bind.annotation.RequestMapping * *(..)) "
            + "&& within(@org.springframework.stereotype.Controller *)")
    public void logPointCut() {
    }

}
