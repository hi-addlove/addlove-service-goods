package com.addlove.service.goods.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
/**
 * 工具转行类
 * 将list转换成map对象方便遍历
 * @author lw
 */
public class MapUtil {
    /**
     * 默认分隔符
     */
    public static final String DEFAULT_KEY_SEPARATOR = "_";

    /**
     * 组件对象
     * @param list 数据
     * @param key 参数名称
     * @return 组件数据
     */
    @SuppressWarnings("unchecked")
    public static <K, T> Map<K, T> convertTypeMap(List<T> list, String key) {
        Map<K, T> map = new HashMap<K, T>();
        if (list == null || list.size() == 0 || StringUtils.isBlank(key)) {
            return map;
        }
        try {
            List<Field> fields = getCellFiledList(list.get(0).getClass(), key);
            if (fields.size() == 0) {
                return map;
            }
            Field f = fields.get(0);
            for (T model : list) {
                map.put((K) f.get(model), model);
            }
        } catch (Exception e) {
            throw new RuntimeException("获取字段属性异常");
        }
        return map;
    }

    /**
     * 组件对象
     * @param list 数据
     * @param key 参数名称
     * @return 组件
     */
    public static <T> Map<String, T> convertMap(List<T> list, String... key) {
        return convertMap(DEFAULT_KEY_SEPARATOR, list, key);
    }

    /**
     * 组件对象
     * @param list 列表
     * @param separator 分割负号
     * @param key 解析列表
     * @return 组件
     */
    public static <T> Map<String, T> convertMap(String separator, List<T> list, String... key) {
        Map<String, T> map = new HashMap<String, T>();
        if (list == null || list.size() == 0 || key == null || key.length == 0) {
            return map;
        }
        try {
            List<Field> fields = getCellFiledList(list.get(0).getClass(), key);
            if (fields.size() == 0) {
                return map;
            }

            for (T model : list) {
                String keys = getMapKey(fields, separator, model);
                map.put(keys, model);
            }
        } catch (Exception e) {
            throw new RuntimeException("获取字段属性异常");
        }
        return map;
    }

    /**
     * 获取map对象的key值,
     * @param field 要取值的字段列表
     * @param separator  分隔符
     * @param model 实例
     * @return 结果
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static <T> String getMapKey(List<Field> field, String separator, T model) throws IllegalArgumentException,
            IllegalAccessException {
        if (StringUtils.isBlank(separator)) {
            separator = DEFAULT_KEY_SEPARATOR;
        }
        StringBuilder strb = new StringBuilder();
        for (Field f : field) {
            strb.append(String.valueOf(f.get(model))).append(separator);
        }
        return strb.substring(0, strb.length() - separator.length());
    }

    /**
     * 获取实例可读取的字段
     * @param class1 实例声明
     * @param key 读取的字段
     * @return Field
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public static <T> List<Field> getCellFiledList(Class<T> class1, String... key) throws NoSuchFieldException,
            SecurityException {
        List<Field> fields = new LinkedList<Field>();
        for (String fieldName : key) {
            Field f = class1.getDeclaredField(fieldName);
            f.setAccessible(true);
            fields.add(f);
        }
        return fields;
    }

    /**
     * 获取实例列表某个属性的值
     * @param list 数据对象
     * @param key 数据实例的某个属性
     * @return 解析结果
     */
    @SuppressWarnings("unchecked")
    public static <T, K> List<K> getFieldValueList(List<T> list, String key) {
        List<K> ret = new LinkedList<K>();
        if (list == null || list.size() == 0 || StringUtils.isBlank(key)) {
            return ret;
        }
        try {
            List<Field> fields = getCellFiledList(list.get(0).getClass(), key);
            if (fields.size() == 0) {
                return ret;
            }
            Field f = fields.get(0);
            for (T model : list) {
                ret.add((K) f.get(model));
            }
        } catch (Exception e) {
            throw new RuntimeException("获取字段属性异常");
        }
        return ret;
    }
}
