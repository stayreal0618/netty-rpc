package com.bj58.pay.starter.proxy;

import com.bj58.pay.annotation.NettyServiceContract;
import com.bj58.pay.rpc.Entry;
import com.bj58.pay.rpc.RequestInfo;
import com.bj58.pay.rpc.ResponseInfo;
import com.bj58.pay.rpc.RpcData;
import com.bj58.pay.rpc.constants.ProtocolVersion;
import com.bj58.pay.rpc.constants.SerializeVersion;
import com.bj58.pay.starter.client.NettyRpcClient;
import io.netty.channel.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @auth stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/28 下午7:11
 */
@AllArgsConstructor
public class StandardProxyHandler implements InvocationHandler {


    private NettyRpcClient nettyRpcClientRunner;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class<?> aClass = method.getDeclaringClass();
        String serverName = aClass.getAnnotation(NettyServiceContract.class).serverName();
        byte serializeVersion = aClass.getAnnotation(NettyServiceContract.class).serializeVersion();
        String simpleName = aClass.getSimpleName();
        String clazzName = aClass.getName();
        String methodName = method.getName();

        List<Entry<String, Object>> list = new ArrayList<>();

        if (args != null) {
            for (Object arg : args) {
                String name = arg.getClass().getName();
                Entry<String, Object> entry = new Entry<>(name, arg);

                list.add(entry);
            }
        }

        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setSessionId(UUID.randomUUID().toString());
        requestInfo.setBeanName(simpleName);
        requestInfo.setClassName(clazzName);
        requestInfo.setServerName(serverName);
        requestInfo.setMethodName(methodName);
        requestInfo.setParam(list);

        RpcData rpcData = new RpcData();
        rpcData.setProtocolVersion(ProtocolVersion.V1);
        rpcData.setSerializeVersion(serializeVersion);
        rpcData.setRequestInfo(requestInfo);

        ResponseInfo responseInfo = nettyRpcClientRunner.sendRequest(rpcData);

        return responseInfo.getResponse();

    }

}
