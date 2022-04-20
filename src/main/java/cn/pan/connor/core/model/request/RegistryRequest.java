package cn.pan.connor.core.model.request;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import cn.pan.connor.core.model.NewService;
import com.google.gson.annotations.SerializedName;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务注册请求
 *
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
public record RegistryRequest(
        // 要注册的service信息
        @SerializedName("service") NewService newService)
        implements RpcCodec {

    public RegistryRequest {
        Assert.notNull(newService);
    }

    @Override
    public String getRpcKind() {
        return RpcKind.REGISTRY;
    }

}
