package com.bjhy.fast.build.web.utils;

import com.bjhy.tlevel.datax.common.Type;
import org.apache.commons.lang3.StringUtils;

/**
 * 表单信息验证工具
 * Create by: Jackson
 */
public class CheckUtil {

    public static boolean checkTargetType(String targetType){
        return StringUtils.equals(Type.TARGET_TYPE_FROM,targetType) || StringUtils.equals(Type.TARGET_TYPE_TO,targetType);
    }


    public static boolean checkAuthority(int authority) {
        return authority== Type.FILE_AUTHORITH_READ ||authority== Type.FILE_AUTHORITH_READ_WRITE;
    }

}
