package com.example.demo1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Author baiyu
 * @Date 2023/3/15 19:02
 * @Description
 */
@Component
public class Bean implements InstantiationAwareBeanPostProcessor {

    @Autowired
    private Environment env;


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        String[] activeProfiles = env.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            System.out.println("11" + activeProfile);
        }

        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }



}
