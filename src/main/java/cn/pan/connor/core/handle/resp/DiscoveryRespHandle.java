package cn.pan.connor.core.handle.resp;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.response.DiscoveryResponse;
import cn.pan.connor.core.transport.ClientCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     服务发现响应handle
 *     触发：{@link cn.pan.connor.core.transport.ConnorClient}
 * </p>
 * @author jixinag
 * @date 2022/4/18
 */
@Slf4j
public class DiscoveryRespHandle extends SimpleChannelInboundHandler<DiscoveryResponse> {
    public static final String NAME = "DiscoveryRespHandle";

	@Override
	protected void channelRead0(ChannelHandlerContext context, DiscoveryResponse response) {
		log.info("accept Connor server discovery service: {}", JsonUtil.toStr(response.getServiceName()));
		ClientCache.ServiceCache.cache(response.getServiceName(),response.getServices());
	}
}