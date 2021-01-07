package com.bj58.pay.starter.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/13 17:12
 */
@Slf4j
public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 6, 4));
        pipeline.addLast(new NettyRpcServerRequestDecoder());
        pipeline.addLast(new NettyRpcServerBusinessHandler());

        pipeline.addLast(new NettyRpcServerResponseEncoder());


    }


}
