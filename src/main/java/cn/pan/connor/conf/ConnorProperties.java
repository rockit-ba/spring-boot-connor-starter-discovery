package cn.pan.connor.conf;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  Hint Attributes 可以使用 枚举定义字段类型
 * @Author Lucky Pan
 * @Date 2022/4/13 20:34
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "connor")
public class ConnorProperties {
    /**
     * connor server 的端口.
     */
    private Integer port = 8080;
    /**
     * connor server 的 host.
     */
    private String connect = "localhost";
}
