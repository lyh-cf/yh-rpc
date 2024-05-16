package encode;

import cn.hutool.core.util.IdUtil;
import com.lyh.rpc.constant.RpcConstant;
import com.lyh.rpc.model.RpcRequest;
import com.lyh.rpc.protocol.ProtocolConstant;
import com.lyh.rpc.protocol.ProtocolMessage;
import com.lyh.rpc.protocol.enums.ProtocolMessageSerializerEnum;
import com.lyh.rpc.protocol.enums.ProtocolMessageStatusEnum;
import com.lyh.rpc.protocol.enums.ProtocolMessageTypeEnum;
import com.lyh.rpc.server.encode.ProtocolMessageDecoder;
import com.lyh.rpc.server.encode.ProtocolMessageEncoder;
import io.vertx.core.buffer.Buffer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/*
 *@title ProtocolMessageTest
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/16 21:17
 */
public class ProtocolMessageTest {

    @Test
    public void testEncodeAndDecode() throws IOException {
        // 构造消息
        ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
        ProtocolMessage.Header header = getHeader();
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceName("myService");
        rpcRequest.setMethodName("myMethod");
        rpcRequest.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        rpcRequest.setParameterTypes(new Class[]{String.class});
        rpcRequest.setArgs(new Object[]{"aaa", "bbb"});
        protocolMessage.setHeader(header);
        protocolMessage.setBody(rpcRequest);

        Buffer encodeBuffer = ProtocolMessageEncoder.encode(protocolMessage);
        ProtocolMessage<?> message = ProtocolMessageDecoder.decode(encodeBuffer);
        Assert.assertNotNull(message);
    }

    private static ProtocolMessage.Header getHeader() {
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) ProtocolMessageSerializerEnum.JDK.getKey());
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setStatus((byte) ProtocolMessageStatusEnum.OK.getValue());
        header.setRequestId(IdUtil.getSnowflakeNextId());
        header.setBodyLength(0);
        return header;
    }

}