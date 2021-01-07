package com.bj58.pay.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/13 14:57
 */

@Data
@ConfigurationProperties( prefix = "netty.rpc.connection")
@Component
public class NettyClientProperties {

    private Map<String, List<ServerInfo>> servers;

}
