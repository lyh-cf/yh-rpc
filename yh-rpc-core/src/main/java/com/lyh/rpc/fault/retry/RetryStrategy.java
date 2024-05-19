package com.lyh.rpc.fault.retry;

/*
 *@title RetryStrategy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/19 19:17
 */

import com.lyh.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * 重试策略
 */
public interface RetryStrategy {

    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}