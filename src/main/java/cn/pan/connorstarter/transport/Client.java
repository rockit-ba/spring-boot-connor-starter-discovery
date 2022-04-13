package cn.pan.connorstarter.transport;

import cn.pan.connorstarter.annos.ConnorRespHandle;
import cn.pan.connorstarter.codec.JsonMagDecoder;
import cn.pan.connorstarter.codec.JsonMagEncoder;
import cn.pan.connorstarter.common.Entry;
import cn.pan.connorstarter.conf.ConnorProperties;
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
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author Lucky Pan
 * @Date 2022/4/13 14:30
 */
@Slf4j
@Configuration
public class Client {
    private final Channel CHANNEL;

    public Client(ConnorProperties connorProperties,
                  ClientChannelInitializer clientChannelInitializer) {
        log.info("clientChannelInitializer：{}",clientChannelInitializer);
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap handler = new Bootstrap()
                .group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(clientChannelInitializer);
        log.info("配置文件：{}",connorProperties);


        ChannelFuture connect = handler.connect(connorProperties.getConnect(), connorProperties.getPort());
        connect.addListener(ele -> {
            if (ele.isSuccess()) {
                log.info("connor server 连接成功");
            }else {
                log.info("connor server 客户端连接失败");
            }
        });
        try {
            connect.sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        this.CHANNEL = connect.channel();

        ChannelFuture closeFuture = CHANNEL.closeFuture();
        closeFuture.addListener(ele ->{
            if (ele.isSuccess()) {
                log.info("连接已关闭");
            }
        });
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
