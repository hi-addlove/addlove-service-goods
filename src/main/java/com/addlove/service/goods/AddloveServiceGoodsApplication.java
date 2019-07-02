package com.addlove.service.goods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.addlove.service.goods.util.LoggerEnhance;

/**
 * 启动类
 * @author lw
 *
 */
@EnableWebMvc
@EnableTransactionManagement
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = { "com.addlove.service.goods" })
public class AddloveServiceGoodsApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddloveServiceGoodsApplication.class);
    public static void main(String[] args) {
        ApplicationContext cxt = SpringApplication.run(AddloveServiceGoodsApplication.class, args);
        String[] activeProfiles = cxt.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            LoggerEnhance.info(LOGGER, "addlove use profile:{}", profile);
        }
    }
}
