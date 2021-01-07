package com.bj58.pay.starter.netty.handler;

import com.bj58.pay.rpc.RequestInfo;
import com.bj58.pay.rpc.RpcData;
import com.bj58.pay.rpc.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @auth stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/12/7 下午4:04
 */
public class NettyRpcClientSendEncoder extends MessageToByteEncoder<RpcData> {


    @Override
    protected void encode(ChannelHandlerContext ctx, RpcData rpcData, ByteBuf out) {

        // 1、首先将需要发送的requestInfo json之后write成byteBuf

        int magicalNumber = RpcData.MAGICAL_NUMBER;
        out.writeInt(magicalNumber);
        out.writeByte(rpcData.getProtocolVersion());
        out.writeByte(rpcData.getSerializeVersion());

        RequestInfo requestInfo = rpcData.getRequestInfo();

        byte[] requestInfoByte = SerializerFactory.getSerializerByVersion(rpcData.getSerializeVersion()).serialize(requestInfo);

        int dataSize = requestInfoByte.length;
        out.writeInt(dataSize);
        out.writeBytes(requestInfoByte);

    }


}
