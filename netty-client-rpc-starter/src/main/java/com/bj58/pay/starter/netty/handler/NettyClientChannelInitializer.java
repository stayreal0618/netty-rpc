package com.bj58.pay.starter.netty.handler;

import com.bj58.pay.rpc.ResponseInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/12/7 下午10:57
 */
public class NettyClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private ConcurrentHashMap<String, SynchronousQueue<ResponseInfo>> responseCollection;

    public NettyClientChannelInitializer(ConcurrentHashMap<String, SynchronousQueue<ResponseInfo>> responseCollection) {
        this.responseCollection = responseCollection;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //inBound
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 6, 4));
        pipeline.addLast(new NettyRpcClientReceiveDecoder());
        pipeline.addLast(new NettyRpcClientReceiveHandler(responseCollection));

        /*********************************分割**********************/
        //outBound
        pipeline.addLast(new NettyRpcClientSendEncoder());
    }

}
