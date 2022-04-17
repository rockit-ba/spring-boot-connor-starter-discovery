package cn.pan.connor.conf;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

import java.util.HashMap;

/**
 * <p>
 *     客户端服务的信息
 * </p>
 * @author jixinag
 * @date 2022/4/16
 */
@Data
public class Discovery {
    /**
     * 服务实例id（因为多个相同的服务可能会注册多个实例，他们的name是相同的）
     */
    private String id = IdUtil.simpleUUID();
    /**
     * 注册的服务名
     */
    private String name;
    /**
     * client service port.
     */
    private Integer port = 8080;
    /**
     * client service host.
     */
    private String host = "localhost";

    /**
     * client service custom data
     */
    private HashMap<String,String> meta = new HashMap<>();
}