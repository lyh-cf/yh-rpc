package com.lyh.rpc.fault.tolerant.impl;

/*
 *@title FailFastTolerantStrategy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 14:49
 */

import com.lyh.rpc.fault.tolerant.TolerantStrategy;
import com.lyh.rpc.model.RpcResponse;

import java.util.Map;

/**
 * 快速失败 - 容错策略（立刻通知外层调用方）
 * 系统出现调用错误时，立刻报错，交给外层调用方处理
 */
public class FailFastTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错", e);
    }
}
