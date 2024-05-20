package com.lyh.rpc.fault.tolerant.impl;

/*
 *@title FailOverTolerantStrategy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 14:57
 */

import com.lyh.rpc.fault.tolerant.TolerantStrategy;
import com.lyh.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 转移到其他服务节点 - 容错策略
 * 一次调用失败后，切换一个其他节点再次进行调用，也算是一种重试
 */
@Slf4j
public class FailOverTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        //todo 获取其他服务节点并调用
        return null;
    }
}
