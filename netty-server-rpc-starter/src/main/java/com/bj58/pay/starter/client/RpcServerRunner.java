package com.bj58.pay.starter.client;

import com.bj58.pay.starter.netty.handler.NettyServerChannelInitializer;
import com.bj58.pay.starter.properties.NettyServerProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/13 15:02
 */
@Slf4j
public class RpcServerRunner implements CommandLineRunner {

    @Resource
    private NettyServerProperties nettyServerProperties;

    EventLoopGroup boss;
    EventLoopGroup worker;
    ServerBootstrap serverBootstrap;


    public void start() {

        try {

            boss = new NioEventLoopGroup(nettyServerProperties.getBossSize());
            worker = new NioEventLoopGroup(nettyServerProperties.getWorkerSize());

            serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(boss, worker)
                    .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new NettyServerChannelInitializer());


            ChannelFuture channelFuture = serverBootstrap.bind(nettyServerProperties.getPort()).sync();

            log.info("*****************netty rpc server started****************");

        } catch (Exception e) {
            log.error("netty rpc server start exception", e);
        } finally {

        }

    }


    public void destroy() {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }

    public void run(String... args) throws Exception {
        start();
    }

}
