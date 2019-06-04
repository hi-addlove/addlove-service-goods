package com.addlove.service.goods.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring 上下文工具类
 * 
 * @author haow 2018-04-15 下午11:03:12
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * 上下文对象
     */
    private static ApplicationContext APPLICATIONCONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextUtil.APPLICATIONCONTEXT == null){
            SpringContextUtil.APPLICATIONCONTEXT  = applicationContext;
        }
        System.out.println("---------------com.migu.ngoc.common.util.SpringContextUtil---------------");
    }


    /**
     * 获取上下文
     *
     * @return context对象
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATIONCONTEXT;
    }

    /**
     * 通过名字获取上下文中的bean
     *
     * @param name bean名称
     * @return bean对象
     */
    public static Object getBean(String name) {
        return APPLICATIONCONTEXT.getBean(name);
    }

    /**
     * 通过类型获取上下文中的bean
     *
     * @param requiredType clazz类型
     * @return bean对象
     */
    public static Object getBean(Class<?> requiredType) {
        return APPLICATIONCONTEXT.getBean(requiredType);
    }
}