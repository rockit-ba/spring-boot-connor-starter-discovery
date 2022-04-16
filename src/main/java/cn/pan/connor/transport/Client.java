package cn.pan.connor.transport;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import cn.pan.connor.codec.RpcCodec;
import cn.pan.connor.conf.ConnorProperties;
import cn.pan.connor.conf.Server;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Connor 客户端实例
 * @author Lucky Pan
 * @date 2022/4/13 14:30
 */
@Slf4j
@Component
public class Client implements InitializingBean {
    @Autowired
    private ConnorProperties connorProperties;
    @Autowired
    ClientChannelInitializer clientChannelInitializer;

    private Channel channel;
    private NioEventLoopGroup loopGroup;

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(clientChannelInitializer,
                () -> new RuntimeException("clientChannelInitializer not null"));
        loopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
        Bootstrap handler = new Bootstrap()
                .group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(clientChannelInitializer);

        Server server = connorProperties.getServer();
        Assert.notNull(server,
                () -> new RuntimeException("server not null"));
        ChannelFuture connect = handler.connect(server.getConnect(), server.getPort());
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
        this.channel = connect.channel();
        log.info("client channel init success");

        ChannelFuture closeFuture = channel.closeFuture();
        closeFuture.addListener(ele ->{
            if (ele.isSuccess()) {
                log.info("channel is closed");
            }
        });
        this.sourceClose();
    }


    /**
     * 发送消息
     * @param rpcCodec 消息体
     * @return ChannelFuture
     */
    public ChannelFuture send(RpcCodec rpcCodec) {
        log.info("send rpc: {}", JSONUtil.toJsonStr(rpcCodec));
        return channel.writeAndFlush(rpcCodec);
    }

    /**
     * 资源关闭回调
     */
    private void sourceClose() {
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                    channel.flush();
                    channel.close();
                    log.info("NioEventLoopGroup source close");
                    loopGroup.shutdownGracefully();
                }, "shutdown"));
    }



}
