package com.lyh.rpc.fault.tolerant;

/*
 *@title TolerantStrategyFactory
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 14:59
 */

import com.lyh.rpc.fault.tolerant.impl.FailFastTolerantStrategy;
import com.lyh.rpc.spi.SpiLoader;

/**
 * 容错策略工厂（工厂模式，用于获取容错策略对象）
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_RETRY_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }

}
