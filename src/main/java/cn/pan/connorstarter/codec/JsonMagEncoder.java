package cn.pan.connorstarter.codec;

import cn.hutool.json.JSONUtil;
import cn.pan.connorstarter.common.Entry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class JsonMagEncoder extends MessageToMessageEncoder<Entry> {

	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, Entry entry, List<Object> list) throws Exception {
		String json = JSONUtil.toJsonStr(entry);
		list.add(json);
	}
}