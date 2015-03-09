package com.baidu.ee.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xuchen04 on 2015/3/2.
 */

@Configuration
@ComponentScan(basePackages = {"com.baidu.ee"})
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebEnvironment(true);
        ConfigurableApplicationContext applicationContext = app.run(args);
        applicationContext.getBeanDefinitionNames();
    }
}
