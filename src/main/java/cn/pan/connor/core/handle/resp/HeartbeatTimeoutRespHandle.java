package cn.pan.connor.core.handle.resp;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.response.HeartbeatTimeoutResponse;
import cn.pan.connor.core.transport.ClientCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  心跳超时响应处理器
 * </p>
 *
 * @author pan
 * @date 2022/5/11 11:37 上午
 */
@Slf4j
public class HeartbeatTimeoutRespHandle extends SimpleChannelInboundHandler<HeartbeatTimeoutResponse> {
    public static final String NAME = "HeartbeatTimeoutRespHandle";
    @Override
    protected void channelRead0(ChannelHandlerContext context, HeartbeatTimeoutResponse response) {
        log.info("accept connor service heartbeatTimeoutRespHandle : {}", JsonUtil.toStr(response));
        ClientCache.ServiceCache.timeoutUpdate(response.getTimeoutServiceIds());
    }
}
