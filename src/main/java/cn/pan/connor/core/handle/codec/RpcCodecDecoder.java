package cn.pan.connor.core.handle.codec;

import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.response.*;
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
            case RpcKind.DISCOVERY_NAMES -> list.add(JsonUtil.toBean(json, DiscoveryServiceNamesResponse.class));
            case RpcKind.SERVICE_CHECK -> list.add(JsonUtil.toBean(json, ServiceCheckResponse.class));
            case RpcKind.DEREGISTRY -> list.add(JsonUtil.toBean(json, DeregistryResponse.class));
            case RpcKind.ADD_SERVICE -> list.add(JsonUtil.toBean(json, AddServiceResponse.class));
            case RpcKind.REMOVE_SERVICE -> list.add(JsonUtil.toBean(json, RemoveServiceResponse.class));
            case RpcKind.HEARTBEAT -> list.add(JsonUtil.toBean(json, HeartbeatResponse.class));
            default -> log.error("Response Parser Fail type:{}, json:{}",type, json);
        }
    }
}
