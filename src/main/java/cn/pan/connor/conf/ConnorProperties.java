package cn.pan.connor.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


/**
 *  所有的配置信息
 *  Hint Attributes 可以使用 枚举定义字段类型
 * @author Lucky Pan
 * @date 2022/4/13 20:34
 */
@Data
@ConfigurationProperties(prefix = "connor")
public class ConnorProperties {
    /**
     * connor server port.
     */
    private Integer port = 8080;
    /**
     * connor server host.
     */
    private String host = "localhost";

    private Lifecycle lifecycle = new Lifecycle();
    /**
     * client server config
     * {@link Discovery}
     */
    @NestedConfigurationProperty
    private Discovery discovery = new Discovery();



    @Data
    public static class Lifecycle {
        private boolean enabled = true;
        @Override
        public String toString() {
            return "Lifecycle{" + "enabled=" + this.enabled + '}';
        }
    }
}
