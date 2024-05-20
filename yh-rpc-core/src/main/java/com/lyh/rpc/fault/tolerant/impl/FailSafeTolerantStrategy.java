package com.lyh.rpc.fault.tolerant.impl;

/*
 *@title FailSafeTolerantStrategy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 14:50
 */

import com.lyh.rpc.fault.tolerant.TolerantStrategy;
import com.lyh.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 静默处理异常 - 容错策略
 * 系统出现部分非重要功能的异常时，直接忽略掉，不做任何处理，就像错误没有发生过一样
 */
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("静默处理异常", e);
        return new RpcResponse();
    }
}