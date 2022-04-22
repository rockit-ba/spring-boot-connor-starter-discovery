package cn.pan.connor.core.model.request;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import cn.pan.connor.core.model.NewService;
import com.google.gson.annotations.SerializedName;
import lombok.extern.slf4j.Slf4j;

/**
 * 下线服务注册请求
 *
 * @author Lucky Pan
 * @date 2022/4/21 11:45
 */
@Slf4j
public record DeregistryRequest(
        @SerializedName("service_name") String serviceName,
        // 要删除的 service_id
        @SerializedName("service_id") String serviceId)
        implements RpcCodec {

    public DeregistryRequest {
        Assert.notNull(serviceName);
        Assert.notNull(serviceId);
    }

    @Override
    public String getRpcKind() {
        return RpcKind.DEREGISTRY;
    }

}
