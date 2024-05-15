package com.lyh.rpc.registry;

/*
 *@title RegistryFactory
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/14 23:52
 */

import com.lyh.rpc.registry.impl.EtcdRegistry;
import com.lyh.rpc.spi.SpiLoader;

/**
 * 注册中心工厂（用于获取注册中心对象）
 */
public class RegistryFactory {

    // SPI 动态加载
    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }

}