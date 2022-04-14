package cn.pan.connor.codec;

import cn.hutool.json.JSONUtil;
import cn.pan.connor.common.Entry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * <p>
 *     json to Entry 解码器
 * </p>
 * @author jixinag
 * @date 2022/4/14
 */
public class JsonMagDecoder extends MessageToMessageDecoder<String> {
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) throws Exception {
		Entry entry = JSONUtil.toBean(s, Entry.class);
		list.add(entry);
	}
}