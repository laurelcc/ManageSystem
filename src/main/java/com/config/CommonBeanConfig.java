package com.config;

import com.handler.CustomBasicErrorController;
import com.handler.CustomHandlerExceptionResolver;
import com.service.storage.StorageProperties;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * Created by soong on 2017/7/22.
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class CommonBeanConfig {

    /**
     * 异常统一处理方式1
     * @return
     */
    @Bean(name = "customBasicErrorController")
    public BasicErrorController errorController(){
        ErrorAttributes attributes = new DefaultErrorAttributes();
        ErrorProperties properties = new ErrorProperties();

        return new CustomBasicErrorController(attributes, properties);
    }

    /**
     * 异常统一处理方式2
     * @return
     */
//    @Bean
//    public CustomHandlerExceptionResolver exceptionResolver(){
//        return new CustomHandlerExceptionResolver();
//    }

    /**
     * 异常统一处理方式3
     * 在controller的方法上使用注解@ExceptionHandler，缺点：只能捕获该controller内的异常
     * 当然，可以通过定义一个基础控制器，然后在其内部声明一个@ExceptionHandler注解过的方法
     */

    /**
     * 异常统一处理方式4
     * 使用@ControllerAdvice注解一个类，然后在其内部定义一个@ExceptionHandler注解过的方法，同方式3类似
     * 优点是覆盖到所有controller控制器上
     */

    /**
     * 异常统一处理方式4
     * @return
     */
//    @Bean
//    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
//        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
//
////        resolver.addStatusCode("", 404);
////        resolver.addStatusCode("", 500);
//
//        return resolver;
//    }

}
