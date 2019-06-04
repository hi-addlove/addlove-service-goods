package com.addlove.service.goods.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 * @author lw
 */
public class ReflectUtil {

    /**
     * 获取类的所有{@link Field}
     *
     * @param object 需要反射的实例
     * @return {@link Field[]}
     */
    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }



    /**
     * 反射
     *
     * @param obj obj
     * @param filedText filed
     * @return result
     * @throws Exception exception
     */
    public static Object getClassDeclaredField(Object obj, String filedText) throws Exception {
        Field field = obj.getClass().getDeclaredField(filedText);

        if (field == null) {
            return null;
        }

        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     * 
     *
     * @param obj obj
     * @param fieldName fieldName
     * @return 对象
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);

        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("GetFieldValue error", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("GetFieldValue error", e);
            }
        }

        return result;
    }

    /**
     * 
     *
     * @param obj obj
     * @param filedText filedText
     * @return 结果
     * @throws Exception
     */
    public static Object getSuperclassDeclaredField(Object obj, String filedText) throws Exception {
        Field field = obj.getClass().getSuperclass().getDeclaredField(filedText);

        if (field == null) {
            return null;
        }

        field.setAccessible(true);
        return field.get(obj);
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * 
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
        Field field = getField(obj, fieldName);

        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("GetFieldValue error", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("GetFieldValue error", e);
            }
        }
    }

    /**
     * 
     *
     * @param obj obj
     * @param fieldName fieldName
     * @return field
     */
    public static Field getField(Object obj, String fieldName) {
        Field field = null;

        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }

        return field;
    }
}
