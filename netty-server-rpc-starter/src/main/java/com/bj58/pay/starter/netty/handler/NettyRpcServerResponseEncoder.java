package com.bj58.pay.starter.netty.handler;

import com.bj58.pay.rpc.ResponseInfo;
import com.bj58.pay.rpc.RpcData;
import com.bj58.pay.rpc.constants.ProtocolVersion;
import com.bj58.pay.rpc.constants.SerializeVersion;
import com.bj58.pay.rpc.serializer.Serializer;
import com.bj58.pay.rpc.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/12/7 下午10:10
 */
public class NettyRpcServerResponseEncoder extends MessageToByteEncoder<RpcData> {

    protected void encode(ChannelHandlerContext ctx, RpcData responseRpcData, ByteBuf out) throws Exception {

        out.writeInt(RpcData.MAGICAL_NUMBER);

        out.writeByte(responseRpcData.getProtocolVersion());
        out.writeByte(responseRpcData.getSerializeVersion());



        Serializer serializer = SerializerFactory.getSerializerByVersion(responseRpcData.getSerializeVersion());
        byte[] responseByte = serializer.serialize(responseRpcData.getResponseInfo());
        int dataLength = responseByte.length;

        out.writeInt(dataLength);
        out.writeBytes(responseByte);

    }


}
