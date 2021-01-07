package com.bj58.pay.starter.spring.configuration;

import com.bj58.pay.starter.annotation.NettyRpcClientEnable;
import com.bj58.pay.starter.client.NettyRpcClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auth stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/13 14:34
 */

@Configuration
@ConditionalOnBean(annotation = NettyRpcClientEnable.class)
public class NettyRpcClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public NettyRpcClient rpcClientRunner() {
        return new NettyRpcClient();
    }

}
