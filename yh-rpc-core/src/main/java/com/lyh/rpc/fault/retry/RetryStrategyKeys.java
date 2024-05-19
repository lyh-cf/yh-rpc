package com.lyh.rpc.fault.retry;

/*
 *@title RetryStrategyKeys
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/19 19:28
 */
/**
 * 重试策略键名常量
 */
public interface RetryStrategyKeys {

    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";

}