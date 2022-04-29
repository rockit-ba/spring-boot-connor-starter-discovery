package cn.pan.connor.core.handle.resp;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.response.RegistryResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     服务注册响应handle
 * </p>
 * @author jixinag
 * @date 2022/4/18
 */
@Slf4j
public class RegistryRespHandle extends SimpleChannelInboundHandler<RegistryResponse> {
    public static final String NAME = "RegistryRespHandle";

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RegistryResponse msg) {
		log.info("accept Connor server registry : {}", JsonUtil.toStr(msg));
	}
}