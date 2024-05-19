package com.lyh.rpc.proxy;

/*
 *@title ServiceProxy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 22:20
 */

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.lyh.rpc.RpcApplication;
import com.lyh.rpc.config.RpcConfig;
import com.lyh.rpc.constant.RpcConstant;
import com.lyh.rpc.fault.retry.RetryStrategy;
import com.lyh.rpc.fault.retry.RetryStrategyFactory;
import com.lyh.rpc.loadbalancer.LoadBalancer;
import com.lyh.rpc.loadbalancer.LoadBalancerFactory;
import com.lyh.rpc.model.RpcRequest;
import com.lyh.rpc.model.RpcResponse;
import com.lyh.rpc.model.ServiceMetaInfo;
import com.lyh.rpc.protocol.ProtocolConstant;
import com.lyh.rpc.protocol.ProtocolMessage;
import com.lyh.rpc.protocol.enums.ProtocolMessageSerializerEnum;
import com.lyh.rpc.protocol.enums.ProtocolMessageTypeEnum;
import com.lyh.rpc.registry.RegistryFactory;
import com.lyh.rpc.serializer.Serializer;
import com.lyh.rpc.serializer.SerializerFactory;
import com.lyh.rpc.registry.Registry;
import com.lyh.rpc.server.encode.ProtocolMessageDecoder;
import com.lyh.rpc.server.encode.ProtocolMessageEncoder;
import com.lyh.rpc.server.tcp.VertxTcpClient;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 服务代理（JDK 动态代理）
 */
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }

            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            // 将调用方法名（请求路径）作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);

            // rpc 请求
            // 使用重试机制
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            RpcResponse rpcResponse = retryStrategy.doRetry(() ->
                    VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo)
            );
            return rpcResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException("调用失败");
        }
    }
}