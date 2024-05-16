package com.lyh.rpc.protocol.enums;

/*
 *@title ProtocolMessageStatusEnum
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/16 20:51
 */

import lombok.Getter;

/**
 * 协议消息的状态枚举
 */
@Getter
public enum ProtocolMessageStatusEnum {
    /*
     * 成功
     */
    OK("ok", 20),
    /*
     * 请求失败
     */
    BAD_REQUEST("badRequest", 40),
    /*
     * 响应失败
     */
    BAD_RESPONSE("badResponse", 50);

    private final String text;

    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum anEnum : ProtocolMessageStatusEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }
}
