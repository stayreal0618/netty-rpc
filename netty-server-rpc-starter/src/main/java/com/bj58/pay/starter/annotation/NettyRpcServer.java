package com.bj58.pay.starter.annotation;

import com.bj58.pay.starter.spring.configuration.NettyApplicationContext;
import com.bj58.pay.starter.spring.configuration.NettyRpcServerConfiguration;
import com.bj58.pay.starter.properties.NettyServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties(NettyServerProperties.class)
@Import({NettyRpcServerConfiguration.class, NettyApplicationContext.class})
public @interface NettyRpcServer {


}