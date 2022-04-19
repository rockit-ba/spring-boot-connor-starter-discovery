package cn.pan.connor.core.model.response;

import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import cn.pan.connor.core.model.NewService;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 服务发现响应
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Data
public class DiscoveryResponse implements RpcCodec {
    @SerializedName("service_name")
    private String serviceName;

    @SerializedName("services")
    private List<NewService> services;

    @Override
    public String getRpcKind() {
        return RpcKind.DISCOVERY;
    }
}
