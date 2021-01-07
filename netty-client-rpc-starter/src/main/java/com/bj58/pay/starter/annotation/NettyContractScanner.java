package com.bj58.pay.starter.annotation;

import com.bj58.pay.starter.spring.configuration.NettyRpcServiceRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@NettyRpcClientEnable
@Import(NettyRpcServiceRegistrar.class)
public @interface NettyContractScanner {

    String[] basePackage();

}
