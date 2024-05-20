package com.lyh.rpc.fault.tolerant.impl;

/*
 *@title FailBackTolerantStrategy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 14:55
 */

import com.lyh.rpc.fault.tolerant.TolerantStrategy;
import com.lyh.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * FailBack - 容错策略
 * 让消费端指定调用失败后要执行的本地服务和方法
 */
@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        //让消费端指定调用失败后要执行的本地服务和方法
        return null;
    }
}
