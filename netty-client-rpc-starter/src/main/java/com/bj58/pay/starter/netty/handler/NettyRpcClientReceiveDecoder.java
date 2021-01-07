package com.bj58.pay.starter.netty.handler;

import com.bj58.pay.rpc.RequestInfo;
import com.bj58.pay.rpc.ResponseInfo;
import com.bj58.pay.rpc.RpcData;
import com.bj58.pay.rpc.serializer.Serializer;
import com.bj58.pay.rpc.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/12/8 下午7:32
 */
public class NettyRpcClientReceiveDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        int magicalNumber = in.readInt();

        //判断是否是跟自己约定的number是否一样，否则认为是无效请求
        if (magicalNumber != RpcData.MAGICAL_NUMBER) {
            ctx.channel().close();
        }

        byte protocolVersion = in.readByte();
        byte serializeVersion = in.readByte();


        int dataLength = in.readInt();

        byte[] bytes = new byte[dataLength];
        in.readBytes(bytes);

        Serializer serializer = SerializerFactory.getSerializerByVersion(serializeVersion);
        ResponseInfo responseInfo = serializer.deserialize(ResponseInfo.class, bytes);

        out.add(responseInfo);

    }

}
