package cn.pan.connorstarter.transport;

import cn.pan.connorstarter.codec.JsonMagDecoder;
import cn.pan.connorstarter.codec.JsonMagEncoder;
import cn.pan.connorstarter.common.Entry;
import cn.pan.connorstarter.inbound.ClientPrintHandle;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @Author Lucky Pan
 * @Date 2022/4/13 14:30
 */
@Slf4j
public class Client {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap handler = new Bootstrap()
                .group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());

        ChannelFuture connect = handler.connect("127.0.0.1", 8080);
        connect.addListener(ele -> {
            if (ele.isSuccess()) {
                log.info("客户端连接成功");
            }else {
                log.info("客户端连接失败");
            }
        });
        connect.sync();
        Channel channel = connect.channel();
        Entry entry = new Entry("蕾姆", 12);
        channel.writeAndFlush(entry);

        ChannelFuture closeFuture = channel.closeFuture();
        closeFuture.addListener(ele ->{
           if (ele.isSuccess()) {
               log.info("连接已关闭");
           }
        });
        closeFuture.sync();
    }

    /**
     * 流水线设置
     */
    static class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) {
            ChannelPipeline pipeline = socketChannel.pipeline();
            // 入站帧解码
            pipeline.addLast(new LengthFieldBasedFrameDecoder(
                    1024*1024*8, 0,
                    4, 0, 4));
            // 出站帧编码
            pipeline.addLast(new LengthFieldPrepender(4));

            // 入站 字节数据解码
            pipeline.addLast(new StringDecoder(StandardCharsets.UTF_8));
            // 出站 字节数据解码
            pipeline.addLast(new StringEncoder(StandardCharsets.UTF_8));

            // 入站 json数据解码
            pipeline.addLast(new JsonMagDecoder());
            // 出站 json数据编码
            pipeline.addLast(new JsonMagEncoder());

            // 入站 请求处理器
            pipeline.addLast(new ClientPrintHandle());

        }

    }
}
