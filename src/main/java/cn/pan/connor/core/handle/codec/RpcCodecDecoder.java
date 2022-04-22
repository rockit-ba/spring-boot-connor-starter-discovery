package cn.pan.connor.core.handle.codec;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.model.response.DiscoveryResponse;
import cn.pan.connor.core.model.response.DiscoveryServiceIdsResponse;
import cn.pan.connor.core.model.response.RegistryResponse;
import cn.pan.connor.core.model.response.ServiceCheckResponse;
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
            case RpcKind.REGISTRY -> list.add(JsonUtil.toBean(json, RegistryResponse.class));
            case RpcKind.DISCOVERY -> list.add(JsonUtil.toBean(json, DiscoveryResponse.class));
            case RpcKind.DISCOVERY_IDS -> list.add(JsonUtil.toBean(json, DiscoveryServiceIdsResponse.class));
            case RpcKind.SERVICE_CHECK -> list.add(JsonUtil.toBean(json, ServiceCheckResponse.class));
            default -> log.error("response parser fail ");
        }
    }
}
