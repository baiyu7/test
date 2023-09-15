package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

/**
 * @Author baiyu
 * @Date 2023/3/15 20:34
 * @Description
 */
public class Test implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
            String applicationName = environment.getProperty("spring.application.name");
            if (!StringUtils.isEmpty(applicationName)){
                System.setProperty("spring.application.name","applicationName");
            }
            String appEnv = environment.getProperty("APP_ENV");
            String registry_url = environment.getProperty("REGISTRY_URL");
    }



}
