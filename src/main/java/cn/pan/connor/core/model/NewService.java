package cn.pan.connor.core.model;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.core.conf.ConnorDiscoveryProperties;
import cn.pan.connor.core.conf.Discovery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * <p>
 *     服务注册信息
 *     用于作为消息体发送给Connor服务端
 * </p>
 * @author jixinag
 * @date 2022/4/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewService {
    private String id;
    private String name;
    private String host;
    private Integer port;
    private Map<String, String> meta;

    public static NewService fromProperties(ConnorDiscoveryProperties properties) {
        Discovery discovery = properties.getDiscovery();
        NewService newService = NewService.builder()
                .id(discovery.getServiceId())
                .name(discovery.getServiceName())
                .host(discovery.getHost())
                .port(discovery.getPort())
                .meta(discovery.getMeta()).build();
        newService.check();
        return newService;
    }

    /**
     * 合规性校验
     */
    public void check() {
        Assert.notBlank(getName(),"NewService#name is not null:{}",getName());
    }
}