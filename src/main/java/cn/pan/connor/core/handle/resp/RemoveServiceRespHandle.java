package cn.pan.connor.core.handle.resp;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.response.RemoveServiceResponse;
import cn.pan.connor.core.transport.ClientCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pan
 * @create 2022/4/29 5:17 下午
 * <p>
 *  服务端主动推送移除某个服务
 * </p>
 */
@Slf4j
public class RemoveServiceRespHandle extends SimpleChannelInboundHandler<RemoveServiceResponse> {
    public static final String NAME = "RemoveServiceRespHandle";
    @Override
    protected void channelRead0(ChannelHandlerContext context, RemoveServiceResponse response) {
        log.info("accept connor server RemoveServiceResponse : {}", JsonUtil.toStr(response));
        ClientCache.ServiceCache.update(response.getServiceName(),response.getServiceList());
    }
}
