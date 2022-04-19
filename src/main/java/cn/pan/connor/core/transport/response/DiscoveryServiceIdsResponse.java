package cn.pan.connor.core.transport.response;

import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 服务发现 获取所有的serviceids 响应
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
@Data
public class DiscoveryServiceIdsResponse implements RpcCodec {
    /**
     * 请求类型
     */
    @SerializedName("rpc_kind")
    private final String rpcKind = RpcKind.DISCOVERY_IDS;

    @SerializedName("service_ids")
    private List<String> serviceIds;

    @Override
    public String getRpcKind() {
        return rpcKind;
    }

}
