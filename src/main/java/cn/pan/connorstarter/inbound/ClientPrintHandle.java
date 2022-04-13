package cn.pan.connorstarter.inbound;

import cn.hutool.json.JSONUtil;
import cn.pan.connorstarter.common.Entry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientPrintHandle extends ChannelInboundHandlerAdapter {
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			Entry entry = (Entry) msg;
			log.info("client 打印处理器：{}", JSONUtil.toJsonStr(entry));
			super.channelRead(ctx, entry);
		}
	}