package com.addlove.service.goods.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import com.bstek.ureport.console.UReportServlet;

/**
 * 配置ureport报表引擎
 * @author lw
 *
 */
@ImportResource("classpath:context.xml")
@Configuration
@ComponentScan(basePackages = {"com.addlove.service.goods.ureport"})
public class UReportConfig {
    @Bean
    public ServletRegistrationBean buildUreportServlet(){
        return new ServletRegistrationBean(new UReportServlet(), "/ureport/*");
    }
}
