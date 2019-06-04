package com.addlove.service.goods.util;

/**
 * 公用方法封装类
 * @author lw
 *
 */
public class ObjTransUtil {

    /**
     * 常量0
     */
    public static final int INT_ZERO = 0;

    /**
     * 常量2
     */
    public static final int INT_TWO = 2;

    /**
     * 常量long 0
     */
    public static final Long LONG_ZERO = 0L;

    /**
     * 获取字符串，为null, 返回""
     *
     * @param para 参数
     * @return 结果
     */
    public static String getStrIgnoreNull(String para) {
        return null == para ? "" : para;
    }

    /**
     * 获取Object的字符串，为null, 返回""
     *
     * @param para 参数
     * @return 结果
     */
    public static String getStrIgnoreObjNull(Object para) {
        return null == para ? "" : para.toString();
    }

    /**
     * 获取Long，为null, 返回0
     *
     * @param num 参数
     * @return 结果
     */
    public static Long getLongIgnoreNull(Long num) {
        return null == num ? LONG_ZERO : num;
    }

    /**
     * 获取Integer，为null, 返回0
     *
     * @param num 参数
     * @return 结果
     */
    public static Integer getIntegerIgnoreNull(Integer num) {
        return null == num ? INT_ZERO : num;
    }

    /**
     * 获取Long，为null, 返回""
     *
     * @param orderId 参数
     * @return 结果
     */
    public static Object getStrIgnoreLongNull(Long orderId) {
        return null == orderId ? "" : orderId.toString();
    }

}
