package com.lyh.rpc.loadbalancer;

/*
 *@title LoadBalancerKeys
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/19 16:41
 */
/**
 * 负载均衡器键名常量
 */
public interface LoadBalancerKeys {

    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";

    String RANDOM = "random";

    String CONSISTENT_HASH = "consistentHash";

}
