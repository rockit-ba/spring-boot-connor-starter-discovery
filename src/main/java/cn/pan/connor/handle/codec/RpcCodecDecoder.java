package cn.pan.connor.handle.codec;

import cn.pan.connor.common.consts.JsonUtil;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.transport.response.RegistryResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 响应入站解码器 byte to {@link RpcCodec}
 * @author Lucky Pan
 * @date 2022/4/14 17:10
 */
@Slf4j
public class RpcCodecDecoder extends ByteToMessageDecoder {
    public static final String NAME = "RpcCodecDecoder";

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf byteBuf, List<Object> list) {
        String content = byteBuf.toString(StandardCharsets.UTF_8);
        byteBuf.clear();
        String type = content.substring(0, 1);
        String json = content.substring(1);

        switch (type) {
            case RpcKind.REGISTRY:
                list.add(JsonUtil.json2pojo(json, RegistryResponse.class));
                break;
            case RpcKind.DISCOVERY:
                break;
            default:
                break;
        }
    }
}
