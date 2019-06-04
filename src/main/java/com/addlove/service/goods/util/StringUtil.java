package com.addlove.service.goods.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lw
 */
public class StringUtil {

    private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);

    /**
     * float 小数后2位数
     */
    private static final int FLOAT_DECIMAL_PLACES = 2;

    /**
     * 字符串转为Long对象
     *
     * @param content
     *            字符串
     * @param defLong
     *            默认Long对象，转换异常是返回
     * @return Long对象
     */
    public static Long getLong(String content, Long defLong) {
        try {
            if (StringUtils.isBlank(content)) {
                return defLong;
            }
            return Long.valueOf(content);
        } catch (Exception e) {
            LOG.warn("字符串转为long对象异常 , content={}, def={}", content, defLong);
            return defLong;
        }
    }

    /**
     * Object转为Long对象
     *
     * @param content
     *            字符串
     * @param defLong
     *            默认Long对象，转换异常是返回
     * @return Long对象
     */
    public static Long getLong(Object content, Long defLong) {
        try {
            if (null == content || StringUtils.isBlank(content.toString())) {
                return defLong;
            }
            return Long.valueOf(content.toString());
        } catch (Exception e) {
            LOG.warn("字符串转为long对象异常, content={}, def={}", content, defLong);
            return defLong;
        }
    }

    /**
     * Integer转为Long对象
     *
     * @param content
     *            字符串
     * @param defLong
     *            默认Long对象，转换异常是返回
     * @return Long对象
     */
    public static Long getLong(Integer content, Long defLong) {
        try {
            if (null == content) {
                return defLong;
            }
            return content.longValue();
        } catch (Exception e) {
            LOG.warn("字符串转为long对象异常,  content={}, def={}", content, defLong);
            return defLong;
        }
    }

    /**
     * 字符串转为long
     *
     * @param content
     *            字符串
     * @param defLong
     *            默认long，转换异常是返回
     * @return Long对象
     */
    public static long parseLong(String content, long defLong) {
        try {
            return Long.parseLong(content);
        } catch (Exception e) {
            LOG.warn("字符串转为long对象异常, content={},  def={}", content, defLong);
            return defLong;
        }
    }

    /**
     * 字符串转为Integer对象
     *
     * @param content
     *            字符串
     * @param defInteger
     *            默认Integer对象，转换异常是返回
     * @return Integer对象
     */
    public static Integer getInteger(String content, Integer defInteger) {
        try {
            return Integer.valueOf(content);
        } catch (Exception e) {
            LOG.warn(" 字符串转为Integer对象异常, content={}, def={}", content, defInteger);
            return defInteger;
        }
    }

    /**
     * 字符串转为Integer对象
     *
     * @param content
     *            字符串
     * @param defInteger
     *            默认Integer对象，转换异常是返回
     * @return Integer对象
     */
    public static Integer getInteger(Object content, Integer defInteger) {
        try {
            if (null == content) {
                return defInteger;
            }

            return Integer.valueOf(content.toString());
        } catch (Exception e) {
            LOG.warn("字符串转为Integer对象异常 , content={}, def={}", content, defInteger);
            return defInteger;
        }
    }

    /**
     * 获取Integer对象
     *
     * @param content
     *            Integer对象
     * @param defInteger
     *            默认Integer对象，转换异常是返回
     * @return Integer对象
     */
    public static Integer getInteger(Integer content, Integer defInteger) {
        return null == content ? defInteger : content;
    }

    /**
     * 获取Integer对象
     *
     * @param content
     *            Long对象
     * @param defInteger
     *            默认Long对象，转换异常是返回
     * @return Long对象
     */
    public static Long getLong(Long content, Long defInteger) {
        return null == content ? defInteger : content;
    }

    /**
     * 字符串转为long
     *
     * @param content
     *            字符串
     * @param defInt
     *            默认int，转换异常是返回
     * @return Long对象
     */
    public static long parseInt(String content, int defInt) {
        try {
            return Integer.parseInt(content);
        } catch (Exception e) {
            LOG.warn("字符串转为Integer对象异常, content={}, def={}", content, defInt);
            return defInt;
        }
    }

    /**
     * 获取字符串 1、当字符串为null 返回 defvalue 2、当字符串trim后如果是空串，返回defvalue 3、当非空字符串，返回trim后的值
     *
     * @param value
     *            值
     * @param defValue
     *            解析失败的默认值
     * @return 解析后的值
     */
    public static String getString(Object value, String defValue) {
        try {
            if (null == value) {
                return defValue;
            }

            if (value.toString().trim().length() <= 0) {
                return defValue;
            }

            return value.toString().trim();
        } catch (Exception e) {
            LOG.warn("Object转为字符串异常 , content={}, def={}", value, defValue);
            return defValue;
        }
    }

    /**
     * 获取字符串 1、当字符串为null 返回 defvalue 2、当字符串trim后如果是空串，返回defvalue 3、当非空字符串，返回trim后的值
     *
     * @param value
     *            值
     * @param defValue
     *            解析失败的默认值
     * @return 解析后的值
     */
    public static String getString(String value, String defValue) {
        try {
            if (null == value) {
                return defValue;
            }

            if (value.trim().length() <= 0) {
                return defValue;
            }

            return value.trim();
        } catch (Exception e) {
            LOG.warn("Object转为字符串异常, content={}, def={}", value, defValue);
            return defValue;
        }
    }

    /**
     * Object转为Long对象
     *
     * @param content
     *            字符串
     * @param defValue
     *            转换异常是返回
     * @return Integer对象
     */
    public static Long parseLong(Object content, long defValue) {
        if (null == content) {
            return defValue;
        }
        try {
            return Long.valueOf(content.toString());
        } catch (Exception e) {
            LOG.warn("字符串转为Long对象异常 , content=, def={}", content, defValue);
            return defValue;
        }
    }

    /**
     * Object转为Integer对象
     *
     * @param content
     *            字符串
     * @param defValue
     *            转换异常是返回
     * @return Integer对象
     */
    public static Integer parseInteger(Object content, int defValue) {
        if (null == content) {
            return defValue;
        }
        try {
            return Integer.valueOf(content.toString());
        } catch (Exception e) {
            LOG.warn("字符串转为Long对象异常, content=, def={}", content, defValue);
            return defValue;
        }
    }

    /**
     * Object转为Float对象,四舍五入，保留2位小数
     *
     * @param content
     *            字符串
     * @param defValue
     *            转换异常是返回
     * @return Float对象
     */
    public static Float parseFloat(Object content, float defValue) {
        return parseFloat(content, -1, defValue);
    }

    /**
     * Object转为Float对象
     *
     * @param content
     *            字符串
     * @param scale
     *            scale
     * @param defValue
     *            转换异常是返回
     * @return Float对象
     */
    public static Float parseFloat(Object content, int scale, float defValue) {
        if (null == content) {
            return defValue;
        }

        if (scale < 0) {
            scale = FLOAT_DECIMAL_PLACES;
        }

        try {
            BigDecimal b = new BigDecimal(content.toString());
            return b.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
        } catch (Exception e) {
            LOG.warn("字符串转为Long对象异常, content=, scale={}, def={}", content, scale, defValue);
            return defValue;
        }
    }

    /**
     * 截取字符中的数字
     *
     * @param str
     *            value
     * @return 数字
     */
    public static String cutOutNumber(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        java.util.regex.Matcher m = p.matcher(str);
        String res = m.replaceAll("").trim();
        return res;
    }
}
