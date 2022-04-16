package cn.pan.connor.transport.request;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.codec.RpcCodec;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.conf.ClientService;
import cn.pan.connor.conf.ConnorProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 服务注册请求
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
@Data
@Component
public class RegistryRequest implements RpcCodec, InitializingBean {
    @Autowired
    private transient ConnorProperties connorProperties;
    /**
     * 请求类型
     */
    private final String rpc_kind = RpcKind.REGISTRY;
    /**
     * 发起服务注册的 信息
     * {@link ClientService}
     */
    private ClientService client_service;

    @Override
    public String getRpcKind() {
        return rpc_kind;
    }

    @Override
    public void afterPropertiesSet() {
        ClientService clientService = connorProperties.getClientService();
        Assert.notNull(clientService,
                () -> new RuntimeException("clientService not null"));
        this.client_service = clientService;
    }
}
