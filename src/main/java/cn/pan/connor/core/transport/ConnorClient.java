package cn.pan.connor.core.transport;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.conf.ConnorDiscoveryProperties;
import cn.pan.connor.core.handle.codec.RpcCodec;
import cn.pan.connor.core.model.request.DiscoveryRequest;
import cn.pan.connor.core.model.request.DiscoveryServiceIdsRequest;
import cn.pan.connor.serviceregistry.ConnorRegistration;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * Connor 客户端实例
 * 用于向服务端发送注册信息 {@link cn.pan.connor.serviceregistry.ConnorServiceRegistry#register(ConnorRegistration)}
 * @author Lucky Pan
 * @date 2022/4/13 14:30
 */
@Slf4j
public class ConnorClient {
    private final Channel channel;
    private final NioEventLoopGroup loopGroup;

    public ConnorClient(ConnorDiscoveryProperties properties) {
        loopGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
        Bootstrap handler = new Bootstrap()
                .group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());

        ChannelFuture connect = handler.connect(properties.getHost(), properties.getPort());
        connect.addListener(ele -> {
            if (ele.isSuccess()) {
                log.info("Connect connor-server success");
            }else {
                log.error("Connect connor-server error");
            }
        });

        try {
            connect.sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        this.channel = connect.channel();
        log.info("Client channel init success");

        ChannelFuture closeFuture = channel.closeFuture();
        closeFuture.addListener(ele ->{
            if (ele.isSuccess()) {
                log.info("Channel is closed");
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
        log.info("Send rpc: {}", JsonUtil.toStr(rpcCodec));
        return channel.writeAndFlush(rpcCodec);
    }

    /**
     * 根据service name  获取 所有的service
     */
    public void asyncGetService(String serviceName) {
        DiscoveryRequest discoveryRequest = new DiscoveryRequest(serviceName);
        this.send(discoveryRequest);
    }

    /**
     * 获取所有的serviceIDS
     */
    public void asyncGetAllServiceIds() {
        this.send(new DiscoveryServiceIdsRequest());
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
