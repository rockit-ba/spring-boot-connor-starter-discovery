package cn.pan.connor.handle.resp;

import cn.hutool.json.JSONUtil;
import cn.pan.connor.transport.response.RegistryResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistryRespHandle extends SimpleChannelInboundHandler<RegistryResponse> {
    public static final String NAME = "RegistryRespHandle";
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RegistryResponse msg) throws Exception {
		log.info("{}", JSONUtil.toJsonStr(msg));
	}
}