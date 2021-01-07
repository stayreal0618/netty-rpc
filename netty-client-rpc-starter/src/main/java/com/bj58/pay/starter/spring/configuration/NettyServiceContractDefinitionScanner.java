package com.bj58.pay.starter.spring.configuration;

import com.bj58.pay.annotation.NettyServiceContract;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @auth stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/12/2 下午5:35
 */
public class NettyServiceContractDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public NettyServiceContractDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }


    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {

        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return (metadata.isIndependent() && metadata.isAnnotated(NettyServiceContract.class.getName()));

    }
}
