package cn.pan.connor.core.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Lucky Pan
 * @date 2022/4/17 16:30
 */
@Data
@ConfigurationProperties(ConnorDiscoveryProperties.PREFIX)
public class ConnorDiscoveryProperties {
    public static final String PREFIX = "spring.cloud.connor";

    /**
     * connor server port.
     */
    private Integer port = 8080;
    /**
     * connor server host.
     */
    private String host = "localhost";

    /**
     * client server config
     * {@link Discovery}
     */
    @NestedConfigurationProperty
    private Discovery discovery = new Discovery();
}
