package com.lyh.rpc.fault.retry.impl;

/*
 *@title FixedIntervalRetryStrategy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/19 19:20
 */

import com.github.rholder.retry.*;
import com.lyh.rpc.fault.retry.RetryStrategy;
import com.lyh.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 固定时间间隔 - 重试策略
 */
@Slf4j
public class FixedIntervalRetryStrategy implements RetryStrategy {

    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws ExecutionException
     * @throws RetryException
     */
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws ExecutionException, RetryException {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                //重试条件
                .retryIfExceptionOfType(Exception.class)
                //重试等待策略，这里选择fixedWait 固定时间间隔策略
                .withWaitStrategy(WaitStrategies.fixedWait(3L, TimeUnit.SECONDS))
                //重试停止策略，选择 stopAfterAttempt 超过最大重试次数停止
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                //重试工作，使用 withRetryListener 监听重试，每次重试时，除了再次执行任务外，还能够打印当前的重试次数
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数 {}", attempt.getAttemptNumber());
                    }
                })
                .build();
        return retryer.call(callable);
    }

}