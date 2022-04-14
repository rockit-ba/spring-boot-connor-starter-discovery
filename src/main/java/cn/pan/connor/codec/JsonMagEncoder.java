package cn.pan.connor.codec;

import cn.hutool.json.JSONUtil;
import cn.pan.connor.common.Entry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * <p>
 *     json to Entry 编码器
 * </p>
 * @author jixinag
 * @date 2022/4/14
 */
public class JsonMagEncoder extends MessageToMessageEncoder<Entry> {
	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, Entry entry, List<Object> list) throws Exception {
		String json = JSONUtil.toJsonStr(entry);
		list.add(json);
	}
}