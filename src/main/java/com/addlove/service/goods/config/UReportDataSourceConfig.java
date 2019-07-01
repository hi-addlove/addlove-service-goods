package com.addlove.service.goods.config;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.addlove.service.goods.util.LoggerEnhance;
import com.bstek.ureport.definition.datasource.BuildinDatasource;

/**
 * 报表引擎数据源配置
 * @author lw
 *
 */
@Component
public class UReportDataSourceConfig implements BuildinDatasource{
    /**UReportDataSourceConfig类日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(UReportDataSourceConfig.class);
    /**数据源名称 */
    private static final String SOURCE_NAME = "oracle_addlove";
    
    @Autowired
    private DataSourceConfig dataSourceConfig;
    
    /* (non-Javadoc)
     * @see com.bstek.ureport.definition.datasource.BuildinDatasource#getConnection()
     */
    @Override
    public Connection getConnection() {
        try {
            return dataSourceConfig.defaultDataSource().getConnection();
        } catch (SQLException e) {
            LoggerEnhance.info(LOGGER, "UReport数据源获取连接失败:{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.bstek.ureport.definition.datasource.BuildinDatasource#name()
     */
    @Override
    public String name() {
        return SOURCE_NAME;
    }
}
