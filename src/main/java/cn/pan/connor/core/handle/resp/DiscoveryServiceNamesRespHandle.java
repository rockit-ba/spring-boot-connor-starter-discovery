package cn.pan.connor.core.handle.resp;

import cn.pan.connor.core.model.response.DiscoveryServiceNamesResponse;
import cn.pan.connor.core.transport.ClientCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     服务发现:获取所有的service-names 响应handle
 * </p>
 * @author jixinag
 * @date 2022/4/18
 */
@Slf4j
public class DiscoveryServiceNamesRespHandle extends SimpleChannelInboundHandler<DiscoveryServiceNamesResponse> {
    public static final String NAME = "DiscoveryServiceNamesRespHandle";

	@Override
	protected void channelRead0(ChannelHandlerContext context, DiscoveryServiceNamesResponse response) {
		log.info("accept Connor server discovery service-names： {}", response.getServiceNames());
		ClientCache.ServiceNamesCache.cache(response.getServiceNames());
	}
}