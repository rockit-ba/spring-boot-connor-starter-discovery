package cn.pan.connor.core.transport.request;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import cn.pan.connor.core.model.NewService;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务注册请求
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
@Data
public class RegistryRequest implements RpcCodec {
    /**
     * 请求类型
     */
    @SerializedName("rpc_kind")
    private final String rpcKind = RpcKind.REGISTRY;
    /**
     * 发起服务注册的 信息
     * {@link NewService}
     */
    @SerializedName("service")
    private final NewService newService;

    public RegistryRequest(NewService newService) {
        Assert.notNull(newService,
                () -> new RuntimeException("service not null"));
        this.newService = newService;
    }

    @Override
    public String getRpcKind() {
        return rpcKind;
    }

}
