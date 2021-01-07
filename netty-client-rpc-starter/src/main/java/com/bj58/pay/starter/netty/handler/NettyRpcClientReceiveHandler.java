package com.bj58.pay.starter.netty.handler;

import com.bj58.pay.rpc.ResponseInfo;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/12/3 下午11:25
 */

public class NettyRpcClientReceiveHandler extends SimpleChannelInboundHandler<ResponseInfo> {

    private ConcurrentHashMap<String, SynchronousQueue<ResponseInfo>> responseCollection;

    public NettyRpcClientReceiveHandler(ConcurrentHashMap<String, SynchronousQueue<ResponseInfo>> responseCollection) {
        this.responseCollection = responseCollection;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseInfo msg) throws Exception {
        String sessionId = msg.getSessionId();
        SynchronousQueue<ResponseInfo> responseInfo = responseCollection.get(sessionId);
        responseInfo.add(msg);
        responseCollection.remove(sessionId);
    }

}
