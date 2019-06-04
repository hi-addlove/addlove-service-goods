package com.addlove.service.goods.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 动态数据源切换aspect
 * @author lw
 *
 */
@Aspect
@Component
// @Order(-10)//保证该AOP在@Transactional之前执行
public class DynamicDataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 动态数据源切换
     *
     * @param point 链接点
     */
    @SuppressWarnings("rawtypes")
    @Before("@annotation(DataSource)")
    public void beforeSwitchDS(JoinPoint point) {
        // 获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        // 获得访问的方法名
        String methodName = point.getSignature().getName();
        // 得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSource = DataSourceConstants.ACCESS_AUTH_DATASOURCE_NAME;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DataSource注解
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource annotation = method.getAnnotation(DataSource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            logger.error("exception:{}", e);
        }
        // 切换数据源
        DataSourceContextHolder.setDataSource(dataSource);
    }

    /**
     * 切换后清除操作
     *
     * @param point 链接点
     */
    @After("@annotation(DataSource)")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDataSource();
    }
}
