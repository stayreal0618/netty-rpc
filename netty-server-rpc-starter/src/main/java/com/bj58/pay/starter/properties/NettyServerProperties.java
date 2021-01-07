package com.bj58.pay.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/13 14:57
 */
@Data
@ConfigurationProperties( prefix = "netty.rpc.server")
public class NettyServerProperties {

    private int bossSize;
    private int workerSize;
    private int port;

}
