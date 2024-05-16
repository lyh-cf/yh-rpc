package com.lyh.rpc.protocol;

/*
 *@title ProtocolConstant
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/16 20:50
 */
/**
 * 协议常量
 */
public interface ProtocolConstant {

    /**
     * 消息头长度
     */
    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔数
     */
    byte PROTOCOL_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 0x1;
}