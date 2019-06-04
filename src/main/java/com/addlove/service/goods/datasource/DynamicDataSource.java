package com.addlove.service.goods.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * @author lw
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 封装数据源的选择逻辑
     * @return 数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

}
