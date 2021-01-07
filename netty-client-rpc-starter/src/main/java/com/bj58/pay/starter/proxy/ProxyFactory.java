package com.bj58.pay.starter.proxy;

import com.bj58.pay.starter.client.NettyRpcClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * @author yy
 * @version 1.0v
 * @description
 * @date 2020/11/28 下午7:09
 */
public class ProxyFactory<T> implements FactoryBean<T> {


    private Class<?> clazz;

    @Autowired
    private NettyRpcClient rpcClientRunner;

    public ProxyFactory() {

    }

    public ProxyFactory(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {clazz}, new StandardProxyHandler(rpcClientRunner));
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }
}
