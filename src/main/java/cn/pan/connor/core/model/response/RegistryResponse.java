package cn.pan.connor.core.model.response;

import cn.pan.connor.core.handle.codec.RpcCodec;
import cn.pan.connor.common.consts.RpcKind;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 服务注册响应
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Data
public class RegistryResponse{
    @SerializedName("service_name")
    private String serviceName;

    private boolean success;
}
