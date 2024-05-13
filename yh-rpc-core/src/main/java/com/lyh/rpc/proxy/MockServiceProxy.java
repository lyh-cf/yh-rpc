package com.lyh.rpc.proxy;

/*
 *@title MockServiceProxy
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/13 20:18
 */

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock 服务代理（JDK 动态代理）
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {
    //策略存储容器
    private static final Map<Class<?>, Object> DEFAULT_VALUE = new HashMap<>();

    static {
        DEFAULT_VALUE.put(boolean.class, false);
        DEFAULT_VALUE.put(short.class, (short) 0);
        DEFAULT_VALUE.put(int.class, 0);
        DEFAULT_VALUE.put(long.class, 0L);
    }

    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 根据方法的返回值类型，生成特定的默认值对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * 生成指定类型的默认值对象（可自行完善默认值逻辑）
     *
     * @param type
     * @return
     */
    private Object getDefaultObject(Class<?> type) {
        // 基本类型
        if (type.isPrimitive()) {//判断Class是否为原始类型
            return DEFAULT_VALUE.get(type);
        }
        // 对象类型
        return null;
    }
}