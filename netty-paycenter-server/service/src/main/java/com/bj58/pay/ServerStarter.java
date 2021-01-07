package com.bj58.pay;

import com.bj58.pay.starter.annotation.NettyRpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/13 17:49
 */

@SpringBootApplication
@NettyRpcServer
public class ServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ServerStarter.class, args);
    }

}
