package com.bj58.pay.starter.client;

import com.bj58.pay.rpc.RequestInfo;
import com.bj58.pay.rpc.ResponseInfo;
import com.bj58.pay.rpc.RpcData;
import com.bj58.pay.starter.netty.handler.NettyClientChannelInitializer;
import com.bj58.pay.starter.properties.NettyClientProperties;
import com.bj58.pay.starter.properties.ServerInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @auth stayreal0618
 * @version 1.0v
 * @description
 * @date 2020/11/13 15:02
 */
@Slf4j
public class NettyRpcClient implements CommandLineRunner {

    @Resource
    private NettyClientProperties nettyProperties;

    @Getter
    private Map<String, List<Channel>> mapChannels = new HashMap<>();

    private ConcurrentHashMap<String, SynchronousQueue<ResponseInfo>> receiveResponseQueue = new ConcurrentHashMap<>();

    public NettyRpcClient() {
    }

    public ResponseInfo sendRequest(RpcData rpcData) throws InterruptedException {

        RequestInfo requestInfo = rpcData.getRequestInfo();

        String serverName = requestInfo.getServerName();
        String sessionId = requestInfo.getSessionId();

        List<Channel> channels = mapChannels.get(serverName);

        Channel channel = channels.get(new Random(RpcData.MAGICAL_NUMBER).nextInt(channels.size()));

        SynchronousQueue<ResponseInfo> synchronousQueue = new SynchronousQueue<>();
        receiveResponseQueue.put(sessionId, synchronousQueue);

        channel.writeAndFlush(rpcData);

        return synchronousQueue.poll(30L, TimeUnit.SECONDS);

    }


    @Override
    public void run(String... args) throws Exception {

        initChannelServer();

    }

    private void initChannelServer() {

        Set<String> keySet = nettyProperties.getServers().keySet();

        for (String serviceName : keySet) {

            try {

                List<ServerInfo> serverInfo = nettyProperties.getServers().get(serviceName);
                log.info("********** start init service [{}], serverInfo [{}] *************", serviceName, serverInfo);

                if (serverInfo == null || serverInfo.isEmpty()) {
                    continue;
                }

                EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.handler(new NettyClientChannelInitializer(receiveResponseQueue));

                List<Channel> channels = new ArrayList<>();

                for (ServerInfo addressInfo : serverInfo) {

                    ChannelFuture channelFuture = bootstrap.connect(addressInfo.getIp(), addressInfo.getPort());

                    channelFuture.addListener((future) -> {
                        if(future.isSuccess()) {
                            channels.add(channelFuture.channel());
                            log.info("************ addressInfo {} connect success **********", addressInfo);
                        }
                    });

                }

                mapChannels.put(serviceName, channels);

            } catch (Exception e) {
                log.error("********* create connect exception , serviceName = {}", serviceName, e);
            }

        }
    }
}
