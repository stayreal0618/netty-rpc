package com.bj58.pay.client;

import com.bj58.pay.starter.annotation.NettyContractScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @auth stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/12/4 下午7:10
 */
@SpringBootApplication
@EnableConfigurationProperties
@NettyContractScanner(basePackage = "com.bj58.pay")
public class SpringBootClient {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootClient.class, args);
    }

}
