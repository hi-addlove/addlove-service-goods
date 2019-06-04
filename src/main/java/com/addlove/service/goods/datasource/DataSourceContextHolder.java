package com.addlove.service.goods.datasource;

/**
 * 数据源容器
 * @author lw
 *
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源名
     *
     * @param dataSourceName
     *            数据源
     */
    public static void setDataSource(String dataSourceName) {
        CONTEXT_HOLDER.set(dataSourceName);
    }

    /**
     * 获取数据源名
     *
     * @return 数据源名
     */
    public static String getDataSource() {
        return (CONTEXT_HOLDER.get());
    }

    /**
     * 清除数据源名
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
