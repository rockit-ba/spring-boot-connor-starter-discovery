package cn.pan.connor.core.model.request;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import com.google.gson.annotations.SerializedName;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     心跳检测请求
 * </p>
 * @author  pan
 * @date  2022/5/4 3:23 下午
 **/
@Slf4j
public record HeartbeatRequest(
        @SerializedName("service_id") String serviceId)
        implements RpcCodec {

    public HeartbeatRequest {
        Assert.notNull(serviceId);
    }

    @Override
    public String getRpcKind() {
        return RpcKind.HEARTBEAT;
    }

}
