package com.bjhy.fast.build.core.domain.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;


/**
 * Create by: Jackson
 */
public class DateCopyUtil {

    /**
     * 将字段名相同的非Null属性进行复制
     *
     * @param source          源对象
     * @param des             复制到的对象
     */
    public static void copy(Object source, Object des) {
        copy(source, des, null);
    }

    /**
     * 将字段名相同的非Null属性进行复制
     *
     * @param source          源对象
     * @param des             复制到的对象
     * @param exceptFieldName 不需要复制的字段
     */
    public static void copy(Object source, Object des, String exceptFieldName) {
        LinkedList<F> checkField = checkField(source, des, exceptFieldName);
        for (F f : checkField) {
            f.source.setAccessible(true);
            try {
                Object sourceObj = f.source.get(source);
                if (sourceObj != null) {
                    f.des.setAccessible(true);
                    f.des.set(des, sourceObj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    public static LinkedList<F> checkField(Object source, Object des, String exceptFieldName) {
        LinkedList<F> fields = new LinkedList<>();
        Field[] sFields = source.getClass().getDeclaredFields();
        Field[] dFields = des.getClass().getDeclaredFields();
        for (Field df : dFields) {
            if(Modifier.isFinal(df.getModifiers())){
                continue;
            }
            String dfName = df.getName();
            if (!StringUtils.isEmpty(exceptFieldName))
                if (StringUtils.equals(dfName, exceptFieldName)) continue;
            for (Field sf : sFields) {
                if (StringUtils.equals(dfName, sf.getName())) {
                    fields.add(new F(sf, df));
                }
            }
        }
        return fields;
    }


    public static class F {
        public F(Field source, Field des) {
            this.source = source;
            this.des = des;
        }

        public Field getSource() {
            return source;
        }

        public void setSource(Field source) {
            this.source = source;
        }

        public Field getDes() {
            return des;
        }

        public void setDes(Field des) {
            this.des = des;
        }

        private Field source;//源
        private Field des;//目标复制对象
    }


}
