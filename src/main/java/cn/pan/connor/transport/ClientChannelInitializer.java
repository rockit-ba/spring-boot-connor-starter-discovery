package cn.pan.connor.transport;

import cn.pan.connor.annos.ConnorRespHandle;
import cn.pan.connor.codec.RpcCodecDecoder;
import cn.pan.connor.codec.RpcCodecEncoder;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeMap;

/**
 * <p>
 *     pipeline handle 初始化
 * </p>
 * @author jixinag
 * @date 2022/4/14
 */
@Slf4j
@Component
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    private List<ChannelInboundHandlerAdapter> channelInboundHandlerAdapters;

    /**
     * 全局处理器流水线
     */
    private ChannelPipeline pipeline;

    /**
     * 基础的初始化
     * @param socketChannel {@link SocketChannel}
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        pipeline = socketChannel.pipeline();
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

        // 添加用户自定义的 handle
        TreeMap<Integer, ChannelInboundHandlerAdapter> treeMap = new TreeMap<>();

        channelInboundHandlerAdapters.forEach(bean -> {
            ConnorRespHandle annotation = AnnotationUtils.getAnnotation(bean.getClass(), ConnorRespHandle.class);
            assert annotation != null;

            // 重新排序
            int order = annotation.value();
            if (treeMap.containsKey(order)) {
                throw new RuntimeException("There are multiple ChannelInboundHandlerAdapter，Need to add order for them");
            }
            treeMap.put(order, bean);
        });

        // 如果自定义的handle有多个，则根据 order 值新兴排序添加
        treeMap.values().forEach(handlerAdapter -> {
            log.info("add ConnorRespHandle  {}",handlerAdapter.getClass().getName());
            this.pipeline.addLast(handlerAdapter);
        });

    }

}