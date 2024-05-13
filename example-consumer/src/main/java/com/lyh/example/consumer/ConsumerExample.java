package com.lyh.example.consumer;

import com.lyh.rpc.config.RpcConfig;
import com.lyh.rpc.utils.ConfigUtils;

/*
 *@title ConsumerExample
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/13 16:59
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}