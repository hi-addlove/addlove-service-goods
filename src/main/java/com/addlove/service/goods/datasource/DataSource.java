package com.addlove.service.goods.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源注解
 * @author lw
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DataSource {

    /**
     * 获取数据源名称
     * @return 数据源名称
     */
    String value() default DataSourceConstants.ACCESS_AUTH_DATASOURCE_NAME;
}
