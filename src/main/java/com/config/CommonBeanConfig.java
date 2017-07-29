package com.config;

import com.handler.CustomBasicErrorController;
import com.service.storage.StorageProperties;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by soong on 2017/7/22.
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class CommonBeanConfig {

    @Bean(name = "customBasicErrorController")
    public BasicErrorController errorController(){
        ErrorAttributes attributes = new DefaultErrorAttributes();
        ErrorProperties properties = new ErrorProperties();

        return new CustomBasicErrorController(attributes, properties);
    }

}
