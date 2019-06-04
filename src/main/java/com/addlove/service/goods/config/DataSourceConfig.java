package com.addlove.service.goods.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import com.addlove.service.goods.datasource.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

/**
 * MyBatis基础配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.addlove.service.goods.dao")
public class DataSourceConfig implements TransactionManagementConfigurer {

    /**
     * 配置默认数据源:oracle
     * @return 动态数据源
     */
    @Bean(name = "defaultDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource defaultDataSource() {
        return new DruidDataSource();
    }
    
    /**
     * mysql数据源
     * @return
     */
    @Bean(name = "mysqlDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDatasource() {
        return new DruidDataSource();
    }

    /**
     * 配置动态数据源
     * @return 动态数据源
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(this.defaultDataSource());
        // 配置多数据源，依次加入其他数据源到map中
        Map<Object, Object> dsMap = new HashMap<Object, Object>();
        dsMap.put("defaultDataSource", this.defaultDataSource());
        dsMap.put("mysqlDatasource", this.mysqlDatasource());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 创建sqlSessionFactory对象
     * @return sqlSessionFactory对象
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource());
        // 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        
        // 添加插件
        bean.setPlugins(new Interceptor[] {pageHelper});
        // 添加sqlmapper目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:sqlmap/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * spring 事务管理
     */
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(this.dynamicDataSource());
    }
}
