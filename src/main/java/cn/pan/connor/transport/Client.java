package cn.pan.connor.transport;

import cn.pan.connor.common.Entry;
import cn.pan.connor.conf.ConnorProperties;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Lucky Pan
 * @date 2022/4/13 14:30
 */
@Slf4j
public class Client {
    private final Channel CHANNEL;

    public Client(ConnorProperties connorProperties,
                  ClientChannelInitializer clientChannelInitializer) {

        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap handler = new Bootstrap()
                .group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(clientChannelInitializer);

        ChannelFuture connect = handler.connect(connorProperties.getConnect(), connorProperties.getPort());
        connect.addListener(ele -> {
            if (ele.isSuccess()) {
                log.info("connect connor-server success");
            }else {
                log.error("connect connor-server error");
            }
        });
        try {
            connect.sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        this.CHANNEL = connect.channel();
        log.info("client channel init success");

        ChannelFuture closeFuture = CHANNEL.closeFuture();
        closeFuture.addListener(ele ->{
            if (ele.isSuccess()) {
                log.info("channel is closed");
            }
        });
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                    CHANNEL.flush();
                    CHANNEL.close();
                    log.info("NioEventLoopGroup source close");
                    loopGroup.shutdownGracefully();
                }, "shutdown"));
    }

    /**
     * 发送消息
     * @param entry 消息体
     * @return ChannelFuture
     */
    public ChannelFuture send(Entry entry) {
        return CHANNEL.writeAndFlush(entry);
    }


}
