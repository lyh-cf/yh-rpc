package com.lyh.rpc.bootstrap;

/*
 *@title ConsumerBootstrap
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 16:26
 */

import com.lyh.rpc.RpcApplication;

/**
 * 服务消费者启动类（初始化）
 */
public class ConsumerBootstrap {

    /**
     * 初始化
     */
    public static void init() {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }

}
