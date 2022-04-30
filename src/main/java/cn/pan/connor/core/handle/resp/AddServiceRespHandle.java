package cn.pan.connor.core.handle.resp;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.NewService;
import cn.pan.connor.core.model.response.AddServiceResponse;
import cn.pan.connor.core.transport.ClientCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pan
 * @create 2022/4/29 5:13 下午
 * <p>
 *  服务端主动响应的添加服务的处理器
 * </p>
 */
@Slf4j
public class AddServiceRespHandle extends SimpleChannelInboundHandler<AddServiceResponse> {
    public static final String NAME = "AddServiceRespHandle";
    @Override
    protected void channelRead0(ChannelHandlerContext context, AddServiceResponse response) {
        log.info("accept connor service AddServiceResponse : {}", JsonUtil.toStr(response));
        ClientCache.ServiceCache.update(response.getServiceName(),response.getServiceList());
    }
}
