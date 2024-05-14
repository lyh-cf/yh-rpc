package com.lyh.rpc.serializer;

/*
 *@title SerializerFactory
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/13 22:26
 */

import com.lyh.rpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂（用于获取序列化器对象）
 *
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }

}