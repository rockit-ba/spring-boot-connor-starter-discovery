package cn.pan.connor.transport;

import cn.pan.connor.annos.ConnorRespHandle;
import cn.pan.connor.codec.JsonMagDecoder;
import cn.pan.connor.codec.JsonMagEncoder;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 *     pipline handle 初始化
 * </p>
 * @author jixinag
 * @date 2022/4/14
 */
@Slf4j
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * 全局处理器流水线
     */
    private ChannelPipeline pipeline;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        pipeline = socketChannel.pipeline();
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

    }

    /**
     * 添加使用方自定义的 入站处理器
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        //注意 需要是根容器才能通过注解获取到bean，比如event直接获取的容器中只有一些公共注册bean
        if (applicationContext.getParent() != null) {
            applicationContext = applicationContext.getParent();
        }
        TreeMap<Integer, ChannelInboundHandlerAdapter> treeMap = new TreeMap<>();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(ConnorRespHandle.class);
        for (Object bean : beansWithAnnotation.values()) {
            ChannelInboundHandlerAdapter clientPrintHandle = (ChannelInboundHandlerAdapter) bean;
            ConnorRespHandle annotation = AnnotationUtils.getAnnotation(bean.getClass(), ConnorRespHandle.class);

            int order = annotation.value();
            if (treeMap.containsKey(order)) {
                throw new RuntimeException("There are multiple ChannelInboundHandlerAdapter，Need to add order for them");
            }
            treeMap.put(order, clientPrintHandle);
        }

        treeMap.values().forEach(ele -> {
            log.info("add ConnorRespHandle  {}",ele.getClass().getName());
            this.pipeline.addLast(ele);
        });

    }
}