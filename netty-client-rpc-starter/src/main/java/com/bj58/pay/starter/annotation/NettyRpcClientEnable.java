package com.bj58.pay.starter.annotation;


import com.bj58.pay.starter.properties.NettyClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties(NettyClientProperties.class)
public @interface NettyRpcClientEnable {

}
