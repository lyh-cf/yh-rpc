package com.lyh.rpc.fault.retry.impl;

/*
 *@title NoRetryStrategy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/19 19:20
 */

import com.lyh.rpc.fault.retry.RetryStrategy;
import com.lyh.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * 不重试 - 重试策略
 */
@Slf4j
public class NoRetryStrategy implements RetryStrategy {

    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws Exception
     */
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }

}
