package com.bj58.pay.starter.spring.configuration;

import com.bj58.pay.annotation.NettyServiceContract;
import com.bj58.pay.starter.annotation.NettyContractScanner;
import com.bj58.pay.starter.proxy.ProxyFactory;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @auth stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/30 下午3:46
 */
public class NettyRpcServiceRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware, ApplicationContextAware {

    @Getter
    private ResourceLoader resourceLoader;

    @Getter
    private Environment environment;

    @Getter
    private ApplicationContext applicationContext;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(NettyContractScanner.class.getName());
        String[] basePackages = (String[]) annotationAttributes.get("basePackage");

        Set<BeanDefinition> allBeanDefinition = new HashSet<>();

        for (String basePackage : basePackages) {

            NettyServiceContractDefinitionScanner nettyServiceContractDefinitionScanner = new NettyServiceContractDefinitionScanner(registry);
            nettyServiceContractDefinitionScanner.setResourceLoader(resourceLoader);
            nettyServiceContractDefinitionScanner.setEnvironment(environment);
            nettyServiceContractDefinitionScanner.addIncludeFilter(new AnnotationTypeFilter(NettyServiceContract.class));

            allBeanDefinition.addAll(nettyServiceContractDefinitionScanner.findCandidateComponents(basePackage));

        }

        for (BeanDefinition definition : allBeanDefinition) {

            try {

                Class<?> aClass = Class.forName(definition.getBeanClassName());

                if (registry.containsBeanDefinition(aClass.getSimpleName())) {
                    continue;
                }

                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(aClass);
                GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) builder.getRawBeanDefinition();
                genericBeanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, aClass);

                genericBeanDefinition.setDependsOn("rpcClientRunner");

                genericBeanDefinition.setBeanClass(ProxyFactory.class);
                genericBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

                registry.registerBeanDefinition(aClass.getSimpleName(), genericBeanDefinition);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
