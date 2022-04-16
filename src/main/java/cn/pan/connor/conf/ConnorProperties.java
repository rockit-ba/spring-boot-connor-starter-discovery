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
     * client server config
     * {@link ClientService}
     */
    @NestedConfigurationProperty
    private ClientService clientService = new ClientService();

    /**
     * conor server config
     * {@link Server}
     */
    @NestedConfigurationProperty
    private Server server = new Server();

}
