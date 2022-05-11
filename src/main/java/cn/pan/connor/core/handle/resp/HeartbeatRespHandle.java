package cn.pan.connor.core.handle.resp;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.response.HeartbeatResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 心跳响应处理器
 * </p>
 *
 * @author pan
 * @date 2022/5/10 3:00 下午
 */
@Slf4j
public class HeartbeatRespHandle extends SimpleChannelInboundHandler<HeartbeatResponse> {
    public static final String NAME = "HeartbeatRespHandle";
    @Override
    protected void channelRead0(ChannelHandlerContext context, HeartbeatResponse response) {
        log.info("accept Connor server HeartbeatRespHandle : {}", JsonUtil.toStr(response));
    }
}
