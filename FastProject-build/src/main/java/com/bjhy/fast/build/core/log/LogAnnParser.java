package com.bjhy.fast.build.core.log;

import com.bjhy.fast.build.core.utils.AnnotationUtil;
import org.aspectj.lang.JoinPoint;


/**
 * Create by: Jackson
 */
public class LogAnnParser {

    public static LogDescribe parseLogDescribe(JoinPoint joinPoint){
        return AnnotationUtil.parseMethod(joinPoint,LogDescribe.class);
    }

    public static LogIgnore parseLogIgnore(JoinPoint joinPoint){
        return AnnotationUtil.parseMethodAndClass(joinPoint,LogIgnore.class);
    }
}
