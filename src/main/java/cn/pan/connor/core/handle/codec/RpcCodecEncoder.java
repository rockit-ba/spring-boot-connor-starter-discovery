package cn.pan.connor.core.handle.codec;

import cn.hutool.core.util.StrUtil;
import cn.pan.connor.common.utils.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 *     请求出站编码器 {@link RpcCodec} to byte
 * </p>
 * @author jixinag
 * @date 2022/4/16
 */
@Slf4j
public class RpcCodecEncoder extends MessageToByteEncoder<RpcCodec> {
    public static final String NAME = "RpcCodecEncoder";

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcCodec msg, ByteBuf out) {
        // 请求类型
        String type = msg.getRpcKind();
        // 请求content
        String jsonStr = JsonUtil.toStr(msg);
        String content = StrUtil.format("{}{}",type,jsonStr);
        out.writeCharSequence(content,StandardCharsets.UTF_8);
    }

}

