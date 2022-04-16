package cn.pan.connor.transport.response;

import cn.pan.connor.codec.RpcCodec;
import cn.pan.connor.common.consts.RpcKind;
import lombok.Data;

/**
 * 服务注册响应
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Data
public class RegistryResponse implements RpcCodec {
    private final String rpc_kind = RpcKind.DISCOVERY;
    // TODO
    private String name;
    private Integer age;


    @Override
    public String getRpcKind() {
        return rpc_kind;
    }
}
