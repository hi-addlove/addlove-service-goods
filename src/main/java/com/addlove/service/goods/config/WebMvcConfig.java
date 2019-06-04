package com.addlove.service.goods.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.addlove.service.goods.intercept.PermissionIntercept;

/**
 * Web mvc配置
 * @author lw
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 设值静态资源能直接访问
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 配置消息类型转换
     *
     * @param converters 消息转换集合
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }

    /**
     * 新增拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链 (addPathPatterns, excludePathPatterns )
        registry.addInterceptor(new PermissionIntercept());
        super.addInterceptors(registry);
    }

    /**
     * 创建Validator对象
     *
     * @return Validator对象.
     */
    @Bean
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        try {
            validator.setValidationMessageSource(getMessageSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validator;
    }

    /**
     * 添加message信息
     *
     * @return 资源对象
     * @throws Exception
     *             异常
     */
    public ReloadableResourceBundleMessageSource getMessageSource() throws Exception {
        ReloadableResourceBundleMessageSource rbms = new ReloadableResourceBundleMessageSource();
        rbms.setDefaultEncoding("UTF-8");
        rbms.setBasenames("i18n/ValidationMessages_zh_CN", "i18n/ValidationMessages_service_zh_CN");
        return rbms;
    }
}
