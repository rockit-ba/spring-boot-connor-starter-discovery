package cn.pan.connor.core.model.request;

import cn.pan.connor.common.consts.RpcKind;
import cn.pan.connor.core.handle.codec.RpcCodec;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务发现 获取所有的service names 请求
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
public record DiscoveryServiceNamesRequest() implements RpcCodec {

    @Override
    public String getRpcKind() {
        return RpcKind.DISCOVERY_NAMES;
    }

}
