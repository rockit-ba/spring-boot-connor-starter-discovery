package cn.pan.connor.core.model;

import cn.hutool.core.lang.Assert;
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

    /**
     * 合规性校验
     */
    public void check() {
        Assert.notBlank(getName(),"NewService#name is not null:{}",getName());
    }
}