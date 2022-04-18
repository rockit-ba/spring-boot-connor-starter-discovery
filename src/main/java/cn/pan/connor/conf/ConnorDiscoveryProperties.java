package cn.pan.connor.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtils.HostInfo;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lucky Pan
 * @date 2022/4/17 16:30
 */
@Data
@Configuration
@ConfigurationProperties(ConnorDiscoveryProperties.PREFIX)
public class ConnorDiscoveryProperties {
    public static final String PREFIX = "spring.cloud.connor.discovery";

    private HostInfo hostInfo;

    private List<String> tags = new ArrayList<>();

    private Map<String, String> metadata = new LinkedHashMap<>();

    private boolean enabled = true;

    private Lifecycle lifecycle = new Lifecycle();

    /**
     * IP address to use when accessing service (must also set preferIpAddress to use).
     */
    private String ipAddress;

    /** Hostname to use when accessing server. */
    private String hostname;

    /** Port to register the service under (defaults to listening port). */
    private Integer port;

    private String serviceName;

    /** Unique service instance id. */
    private String instanceId;

    /** Register as a service in connor. */
    private boolean register = true;

    /** Disable automatic de-registration of service in connor. */
    private boolean deregister = true;

    @SuppressWarnings("unused")
    private ConnorDiscoveryProperties() {
        this(new InetUtils(new InetUtilsProperties()));
    }

    public ConnorDiscoveryProperties(InetUtils inetUtils) {
        this.hostInfo = inetUtils.findFirstNonLoopbackHostInfo();
        this.ipAddress = this.hostInfo.getIpAddress();
        this.hostname = this.hostInfo.getHostname();
    }


    public static class Lifecycle {

        private boolean enabled = true;

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        @Override
        public String toString() {
            return "Lifecycle{" + "enabled=" + this.enabled + '}';
        }

    }
}
