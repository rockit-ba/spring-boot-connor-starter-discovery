package cn.pan.connor.codec;

import cn.hutool.json.JSONUtil;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.transport.response.RegistryResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 响应入站解码器 byte to {@link RpcCodec}
 * @author Lucky Pan
 * @date 2022/4/14 17:10
 */
public class RpcCodecDecoder extends ByteToMessageDecoder {
    public static final String NAME = "RpcCodecDecoder";

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf byteBuf, List<Object> list) {

        String content = byteBuf.toString(StandardCharsets.UTF_8);
        String type = content.substring(0, 1);
        String json = content.substring(1);

        RpcCodec rpcCodec = null;
        switch (type) {
            case RpcKind.REGISTRY:
                rpcCodec = JSONUtil.toBean(json,RegistryResponse.class);
                break;
            case RpcKind.DISCOVERY:
                break;
            default:
                break;
        }

        list.add(rpcCodec);
    }
}
