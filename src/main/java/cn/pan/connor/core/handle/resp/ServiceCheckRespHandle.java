package cn.pan.connor.core.handle.resp;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.response.ServiceCheckResponse;
import cn.pan.connor.core.transport.ClientCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     服务状态检查 响应handle
 * </p>
 * @author jixinag
 * @date 2022/4/18
 */
@Slf4j
public class ServiceCheckRespHandle extends SimpleChannelInboundHandler<ServiceCheckResponse> {
    public static final String NAME = "ServiceCheckRespHandle";

	@Override
	protected void channelRead0(ChannelHandlerContext context, ServiceCheckResponse response) {
		log.info("accept Connor server status check: {}", JsonUtil.toStr(response));
		ClientCache.ServiceCheck.putResp(response.getServiceId());
	}
}