package cn.pan.connor.core.handle.resp;

import cn.pan.connor.core.model.response.DiscoveryResponse;
import cn.pan.connor.core.model.response.DiscoveryServiceIdsResponse;
import cn.pan.connor.discovery.DiscoveryServiceQueue;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     服务发现:获取所有的service-ids 响应handle
 * </p>
 * @author jixinag
 * @date 2022/4/18
 */
@Slf4j
public class DiscoveryServiceIdsRespHandle extends SimpleChannelInboundHandler<DiscoveryServiceIdsResponse> {
    public static final String NAME = "DiscoveryServiceIdsRespHandle";

	@Override
	protected void channelRead0(ChannelHandlerContext context, DiscoveryServiceIdsResponse response) throws Exception {
		DiscoveryServiceQueue.addServices(response.getServiceIds());
	}
}