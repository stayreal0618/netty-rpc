package com.bj58.pay.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NettyServiceContract {

    String lookup();

    String serverName();

    byte serializeVersion();


}
