package com.lyh.rpc.loadbalancer.impl;

/*
 *@title ConsistentHashLoadBalancer
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/19 16:20
 */

import com.lyh.rpc.loadbalancer.LoadBalancer;
import com.lyh.rpc.model.ServiceMetaInfo;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 一致性哈希负载均衡器
 * 每次调用负载均衡器时，都会重新构造 Hash 环，这是为了能够即时处理节点的变化
 */
public class ConsistentHashLoadBalancer implements LoadBalancer {
    /**
     * 一致性 Hash 环，存放虚拟节点
     */
    private final TreeMap<Integer, ServiceMetaInfo> virtualNodes = new TreeMap<>();

    /**
     * 虚拟节点数
     */
    private static final int VIRTUAL_NODE_NUM = 100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) {
            return null;
        }

        // 构建虚拟节点环
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                int hash = getHash(serviceMetaInfo.getServiceAddress() + "#" + i);
                virtualNodes.put(hash, serviceMetaInfo);
            }
        }
        String requestIp = (String) requestParams.get("requestIp");
        // 获取调用请求的 hash 值
        int hash = getHash(requestIp);

        // 选择最接近且大于等于调用请求 hash 值的虚拟节点
        Map.Entry<Integer, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);
        if (entry == null) {
            // 如果没有大于等于调用请求 hash 值的虚拟节点，则返回环首部的节点
            entry = virtualNodes.firstEntry();
        }
        return entry.getValue();
    }


    /**
     * Hash 算法，可自行实现
     * 根据请求客户端的 IP 地址来计算 Hash 值，保证同 IP 的请求发送给相同的服务提供者
     *
     * @param ip
     * @return
     */
    private int getHash(String ip) {
        try {
            // 解析IP地址为InetAddress对象
            InetAddress address = InetAddress.getByName(ip);

            // 获取字节数组表示的IP地址
            byte[] bytes = address.getAddress();

            // 将字节数组转换为BigInteger对象
            BigInteger bigInteger = new BigInteger(1, bytes);

            // 返回BigInteger对象的哈希码
            return bigInteger.hashCode();
        } catch (UnknownHostException e) {
            // 处理解析IP地址出错的情况
            e.printStackTrace();
            return 0; // 或者返回其他默认值
        }
    }
}
