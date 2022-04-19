package cn.pan.connor.core.transport;

import cn.pan.connor.core.handle.codec.RpcCodecDecoder;
import cn.pan.connor.core.handle.codec.RpcCodecEncoder;
import cn.pan.connor.core.handle.resp.DiscoveryRespHandle;
import cn.pan.connor.core.handle.resp.DiscoveryServiceIdsRespHandle;
import cn.pan.connor.core.handle.resp.RegistryRespHandle;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     pipeline handle 初始化
 * </p>
 * @author jixinag
 * @date 2022/4/14
 */
@Slf4j
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 基础的初始化
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        /**
         * 全局处理器流水线
         */
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 入站帧解码
        pipeline.addLast("LengthFieldBasedFrame",
                new LengthFieldBasedFrameDecoder(
                        //maxFrameLength，表示数据包的最大长度为xxx个字节。
                        1024*1024*8,
                        // 设置为0，表示长度字段的偏移量为0，也就是长度字段放在了最前面，处于数据包的起始位置。
                        0,
                        // 表示长度字段的长度为4个字节，即表示内容长度的值占用数据包的4个字节。
                        4,
                        // 长度调整值的计算公式为：内容字段偏移量-长度字段偏移量-长度字段的字节数
                        0,
                        // 表示获取最终内容Content的字节数组时，抛弃最前面的4个字节的数据。
                        4));
        // 出站帧编码
        pipeline.addLast("LengthFieldPrepender",new LengthFieldPrepender(4));

        // 入站 字节转 对象
        pipeline.addLast(RpcCodecDecoder.NAME, new RpcCodecDecoder());
        // 对象转字节
        pipeline.addLast(RpcCodecEncoder.NAME, new RpcCodecEncoder());

        // 入站 RegistryResp
        pipeline.addLast(RegistryRespHandle.NAME, new RegistryRespHandle());
        pipeline.addLast(DiscoveryRespHandle.NAME, new DiscoveryRespHandle());
        pipeline.addLast(DiscoveryServiceIdsRespHandle.NAME, new DiscoveryServiceIdsRespHandle());

    }

}