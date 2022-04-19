package cn.pan.connor.core.model.request;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务发现请求
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
@Data
public class DiscoveryRequest implements RpcCodec {
    /**
     * 请求类型
     */
    @SerializedName("rpc_kind")
    private final String rpcKind = RpcKind.DISCOVERY;
    /**
     * 查询的服务 name
     */
    @SerializedName("service_name")
    private final String serviceName;

    public DiscoveryRequest(String serviceName) {
        Assert.notBlank(serviceName,
                () -> new RuntimeException("serviceName not black"));
        this.serviceName = serviceName;
    }

    @Override
    public String getRpcKind() {
        return rpcKind;
    }

}
