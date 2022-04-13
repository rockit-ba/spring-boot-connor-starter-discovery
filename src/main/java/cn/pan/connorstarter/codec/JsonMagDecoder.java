package cn.pan.connorstarter.codec;

import cn.hutool.json.JSONUtil;
import cn.pan.connorstarter.common.Entry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class JsonMagDecoder extends MessageToMessageDecoder<String> {

		@Override
		protected void decode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) throws Exception {
			Entry entry = JSONUtil.toBean(s, Entry.class);
			list.add(entry);
		}
	}