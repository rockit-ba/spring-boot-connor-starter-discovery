package cn.pan.connor.core.conf;

import cn.hutool.core.util.IdUtil;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.net.URI;
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
    private String serviceId = IdUtil.simpleUUID();
    /**
     * 注册的服务名
     */
    private String serviceName;
    /**
     * client service port.
     */
    private Integer port = 8080;
    /**
     * client service host.
     */
    private String host = "localhost";

    /**
     * 是否安全
     */
    private boolean secure = false;

    /**
     * URI
     */
    private URI uri;

    /**
     * client service custom data
     */
    private HashMap<String,String> meta;

    /**
     * is register
     */
    private boolean register = true;
}