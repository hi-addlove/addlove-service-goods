package com.addlove.service.goods.util;

/**
 * @author lw
 */
public class InterfaceLogThreadLocal {

    private static final ThreadLocal<InterfaceLog> CONTEXT_HOLDER = new ThreadLocal<InterfaceLog>();

    /**
     * 设置用户数据
     *
     * @param log
     *            日志
     */
    public static void setInterfaceCallInfo(InterfaceLog log) {
        CONTEXT_HOLDER.set(log);
    }

    /**
     * 获取用户数据
     *
     * @return 用户对象
     */
    public static InterfaceLog getInterfaceCallInfo() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除用户数据
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * 获取当前序号
     *
     * @return 序号
     */
    public static int getNumber() {
        InterfaceLog info = getInterfaceCallInfo();
        if (null == info) {
            return 0;
        }

        return info.getNumber();
    }

    /**
     * 获取日志key
     *
     * @return 日志key
     */
    public static String getTraceKey() {
        InterfaceLog info = getInterfaceCallInfo();
        if (null == info) {
            return "";
        }

        return info.getTraceKey();
    }
}
