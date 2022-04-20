package cn.pan.connor.core.model.request;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import com.google.gson.annotations.SerializedName;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务发现请求
 *
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
public record DiscoveryRequest(
        // 要获取的服务列表的service-name
        @SerializedName("service_name") String serviceName)
        implements RpcCodec {

    public DiscoveryRequest {
        Assert.notBlank(serviceName);
    }

    @Override
    public String getRpcKind() {
        return RpcKind.DISCOVERY;
    }

}
