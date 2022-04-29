package cn.pan.connor.core.handle.resp;

import cn.pan.connor.core.model.response.DeregistryResponse;
import cn.pan.connor.core.model.response.DiscoveryResponse;
import cn.pan.connor.core.transport.ClientCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * <p>
 *     服务下线响应处理器
 * </p>
 * @author  pan
 * @create  2022/4/29 1:16 下午
 **/
@Slf4j
public class DeregistryRespHandle extends SimpleChannelInboundHandler<DeregistryResponse> {
    public static final String NAME = "DeregistryRespHandle";

	@Override
	protected void channelRead0(ChannelHandlerContext context, DeregistryResponse response) {
		log.info("accept Connor server deregistry service : {}",response.isSuccess());
	}
}