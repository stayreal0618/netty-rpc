package com.bj58.pay.starter.netty.handler;

import com.bj58.pay.rpc.Entry;
import com.bj58.pay.rpc.RequestInfo;
import com.bj58.pay.rpc.ResponseInfo;
import com.bj58.pay.rpc.RpcData;
import com.bj58.pay.starter.spring.configuration.NettyApplicationContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.MethodUtils;

import java.util.List;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/14 17:45
 */
@Slf4j
//@ChannelHandler.Sharable
public class NettyRpcServerBusinessHandler extends SimpleChannelInboundHandler<RpcData> {


    protected void channelRead0(ChannelHandlerContext ctx, RpcData requestRpcData) throws Exception {

        log.debug("handler receive requestRpcData = " + requestRpcData);

        try {
            RequestInfo requestInfo = requestRpcData.getRequestInfo();

            String beanName = requestInfo.getBeanName();
            String messageName = requestInfo.getMethodName();

            Object bean = NettyApplicationContext.context.getBean(beanName);

            Object returnObj;

            List<Entry<String, Object>> param = requestInfo.getParam();

            if (param == null || param.isEmpty()) {
                returnObj = MethodUtils.invokeMethod(bean, messageName, null);
            } else {

                Object[] objs = new Object[param.size()];

                for (int i = 0; i < param.size(); i++) {
                    objs[i] = param.get(i).getValue();
                }

                returnObj = MethodUtils.invokeMethod(bean, messageName, objs);

            }

            RpcData rpcData = new RpcData();

            rpcData.setProtocolVersion(requestRpcData.getProtocolVersion());
            rpcData.setSerializeVersion(requestRpcData.getSerializeVersion());

            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setSessionId(requestInfo.getSessionId());
            responseInfo.setResponse(returnObj);
            rpcData.setResponseInfo(responseInfo);

            ctx.channel().writeAndFlush(rpcData);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("netty rpc client channelRegistered ip: {}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("netty rpc client channelActive ip: {}", ctx.channel().remoteAddress());
    }
}
