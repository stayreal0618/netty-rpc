package com.bj58.pay.starter.spring.configuration;

import com.bj58.pay.starter.client.RpcServerRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bj58.pay.starter.annotation.NettyRpcServer;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/13 14:34
 */

@Configuration
@ConditionalOnBean(annotation = NettyRpcServer.class)
public class NettyRpcServerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RpcServerRunner rpcServerRunner() {
        return new RpcServerRunner();
    }

}
