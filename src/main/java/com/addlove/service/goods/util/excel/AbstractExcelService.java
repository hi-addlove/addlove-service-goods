package com.addlove.service.goods.util.excel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.addlove.service.goods.util.excel.SheetInfo.CellInfo;


/**
 * excel服务
 */
public abstract class AbstractExcelService {
    /**
     * 日志对象
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractExcelService.class);

    /**
     * 将字符数组转行成对象
     * @param fieldData
     * @param fieldMap
     * @param instanceClass
     * @return
     * @throws Exception
     */
    protected static <T> List<T> convertEntityList(List<String[]> list, int skipRow, Class<T> cls) throws Exception {
        List<T> ret = new LinkedList<T>();
        if (list == null) {
            return ret;
        }

        Map<Integer, Field> fieldMap = getCellFiledMap(cls);
        int skipeNumber = 0;
        for (String[] str : list) {
            if (skipeNumber++ < skipRow) {
                continue;
            }
            ret.add(convertEntity(str, fieldMap, cls));
        }
        return ret;
    }

    /**
     * 将字符数组转行成对象
     * @param fieldData
     * @param fieldMap
     * @param instanceClass
     * @return
     * @throws Exception
     */
    protected static <T> T convertEntity(String[] fieldData, Map<Integer, Field> fieldMap, Class<T> instanceClass)
            throws Exception {
        T entity = instanceClass.newInstance();
        for (int i = 0; i < fieldData.length; i++) {
            Field fileld = fieldMap.get(i);
            if (fileld != null) {
                setEntityValue(entity, fieldMap.get(i), fieldData[i]);
            }
        }
        return entity;
    }

    /**
     * 设置字段的值
     * @param entity
     * @param field
     * @param value
     * @throws Exception
     */
    protected static <T> void setEntityValue(T entity, Field field, String value) throws Exception {
        if (field == null || value == null) {
            return;
        }

        Class<?> fieldType = field.getType();
        if (String.class == fieldType) {
            field.set(entity, value);
            return;
        }

        if (Integer.TYPE == fieldType || Integer.class == fieldType) {
            field.set(entity, Integer.valueOf(value));
            return;
        }

        if (Long.TYPE == fieldType || Long.class == fieldType) {
            field.set(entity, Long.valueOf(value));
            return;
        }

        if (BigDecimal.class == fieldType) {
            field.set(entity, new BigDecimal(value.toString()));
            return;
        }

        if (Date.class == fieldType) {
            field.set(entity, DateUtil.parseDate(value));
            return;
        }

        if (Character.TYPE == fieldType) {
            if (value.toString().length() > 0) {
                field.set(entity, value.toString().charAt(0));
            }
            return;
        }

        if (Boolean.TYPE == fieldType || Boolean.class == fieldType) {
            field.set(entity, Boolean.valueOf(value.toString()));
            return;
        }
    }

    /**
     * 解析excel Model的描述
     * @param cls
     * @return
     */
    protected static <T> Map<Integer, Field> getCellFiledMap(Class<T> cls) {
        Map<Integer, Field> fieldMap = new HashMap<Integer, Field>();
        Field[] allFields = cls.getDeclaredFields();
        for (Field field : allFields) {
            CellInfo cellInfo = field.getAnnotation(CellInfo.class);
            if (cellInfo != null) {
                field.setAccessible(true); // 设置实体类私有属性可访问
                fieldMap.put(cellInfo.seq(), field);
            }
        }
        return fieldMap;
    }

    /**
     * 解析excel的描述
     * @param class1
     * @return
     */
    protected static <T> List<Field> getCellFiledList(Class<T> class1) {
        List<Field> fields = new LinkedList<Field>();
        Field[] allFields = class1.getDeclaredFields();
        for (Field field : allFields) {
            if (field.isAnnotationPresent(CellInfo.class)) {
                field.setAccessible(true); // 设置实体类私有属性可访问
                fields.add(field);
            }
        }
        return fields;
    }
}
