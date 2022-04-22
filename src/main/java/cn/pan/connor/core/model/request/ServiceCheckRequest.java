package cn.pan.connor.core.model.request;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import com.google.gson.annotations.SerializedName;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务健康检查请求
 *
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
public record ServiceCheckRequest(
        // 要获取的服务列表的service-name
        @SerializedName("service_id") String serviceId)
        implements RpcCodec {

    public ServiceCheckRequest {
        Assert.notBlank(serviceId);
    }

    @Override
    public String getRpcKind() {
        return RpcKind.SERVICE_CHECK;
    }

}
