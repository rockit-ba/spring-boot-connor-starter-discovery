package cn.pan.connor;

import cn.pan.connor.core.conf.ConnorDiscoveryProperties;
import cn.pan.connor.core.transport.ConnorClient;
import cn.pan.connor.core.transport.HeartbeatSchedule;
import cn.pan.connor.discovery.ConnorDiscoveryClient;
import cn.pan.connor.serviceregistry.ConnorAutoServiceRegistration;
import cn.pan.connor.serviceregistry.ConnorRegistration;
import cn.pan.connor.serviceregistry.ConnorServiceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * connor client 自动装配
 * @author Lucky Pan
 * @date 2022/4/13 20:47
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ConnorDiscoveryProperties.class,AutoServiceRegistrationProperties.class})
@AutoConfigureAfter({ConfigurationPropertiesAutoConfiguration.class})
public class ConnorAutoConfiguration {
    public ConnorAutoConfiguration() {}

    /**
     * 客户端
     * @param properties {@link ConnorDiscoveryProperties}
     * @return Client
     */
    @Bean
    public ConnorClient client(final ConnorDiscoveryProperties properties) {
        return new ConnorClient(properties);
    }

    /**
     * 注册服务类
     * @param properties {@link ConnorDiscoveryProperties}
     * @return ConnorRegistration
     */
    @Bean
    public ConnorRegistration connorRegistration(final ConnorDiscoveryProperties properties) {
        return new ConnorRegistration(properties);
    }

    /**
     * 服务注册类
     * @return ConnorServiceRegistry
     */
    @Bean
    public ConnorServiceRegistry serviceRegistry(final ConnorClient client) {
        return new ConnorServiceRegistry(client);
    }

    /**
     * 服务注册发现客户端
     */
    @Bean
    public ConnorDiscoveryClient connorDiscoveryClient(final ConnorClient connorClient) {
        return new ConnorDiscoveryClient(connorClient);
    }

    /**
     * 自动服务注册 配置
     * @param serviceRegistry {@link ServiceRegistry<ConnorRegistration>}
     * @param autoServiceRegistrationProperties {@link AutoServiceRegistrationProperties}
     * @param registration {@link ConnorRegistration}
     * @param properties {@link ConnorDiscoveryProperties}
     * @return ConnorAutoServiceRegistration
     */
    @Bean
    public ConnorAutoServiceRegistration connorAutoServiceRegistration(
            ServiceRegistry<ConnorRegistration> serviceRegistry,
            AutoServiceRegistrationProperties autoServiceRegistrationProperties,
            ConnorRegistration registration,
            ConnorDiscoveryProperties properties) {
        return new ConnorAutoServiceRegistration(serviceRegistry,autoServiceRegistrationProperties,registration,properties);
    }

    /**
     * 定时向server发送心跳检测数据
     * @param client ConnorClient
     * @return HeartbeatSchedule
     */
    @Bean
    public HeartbeatSchedule heartbeatSchedule(ConnorClient client) {
        return new HeartbeatSchedule(client);
    }

}
