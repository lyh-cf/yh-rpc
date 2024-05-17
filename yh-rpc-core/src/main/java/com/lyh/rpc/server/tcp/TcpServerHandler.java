package com.lyh.rpc.server.tcp;

import com.lyh.rpc.model.RpcRequest;
import com.lyh.rpc.model.RpcResponse;
import com.lyh.rpc.protocol.ProtocolMessage;
import com.lyh.rpc.protocol.enums.ProtocolMessageTypeEnum;
import com.lyh.rpc.registry.LocalRegistry;
import com.lyh.rpc.server.encode.ProtocolMessageDecoder;
import com.lyh.rpc.server.encode.ProtocolMessageEncoder;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.Method;

/*
 *@title TcpServerHandler
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/16 21:19
 */
public class TcpServerHandler implements Handler<NetSocket> {

    /**
     * 处理请求
     *
     * @param socket the event to handle
     */
    @Override
    public void handle(NetSocket socket) {
        TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
            // 处理请求代码
        });
        socket.handler(bufferHandlerWrapper);
    }
}