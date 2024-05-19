package com.lyh.rpc.loadbalancer;

/*
 *@title LoadBalancerFactory
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/19 16:43
 */

import com.lyh.rpc.loadbalancer.impl.RoundRobinLoadBalancer;
import com.lyh.rpc.spi.SpiLoader;

/**
 * 负载均衡器工厂（工厂模式，用于获取负载均衡器对象）
 */
public class LoadBalancerFactory {

    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 默认负载均衡器
     */
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }

}
