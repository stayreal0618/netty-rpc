package com.bj58.pay.starter.spring.configuration;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/12/5 上午10:40
 */
public class BeanContextAware implements EnvironmentAware, ResourceLoaderAware, ApplicationContextAware {

    @Getter
    private static ResourceLoader resourceLoader;

    @Getter
    private static Environment environment;

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanContextAware.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        BeanContextAware.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        BeanContextAware.resourceLoader = resourceLoader;
    }



}
