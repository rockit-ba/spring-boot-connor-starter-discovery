package cn.pan.connor;

import cn.pan.connor.conf.ConnorProperties;
import cn.pan.connor.transport.Client;
import cn.pan.connor.transport.ClientChannelInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * connor client 自动装配
 * @Author Lucky Pan
 * @Date 2022/4/13 20:47
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ConnorProperties.class)
public class ConnorAutoConfiguration implements InitializingBean {
    /**
     * 配置
     */
    private final ConnorProperties connorProperties;

    public ConnorAutoConfiguration(ConnorProperties connorProperties) {
        log.info("config loaded");
        this.connorProperties = connorProperties;
    }

    @Override
    public void afterPropertiesSet() {
        checkConfigFileExists();
    }
    private void checkConfigFileExists() {
        // TODO
    }

    /**
     * 出站、入站处理器装配 初始化器
     * @return ClientChannelInitializer
     */
    @Bean
    public ClientChannelInitializer getClientChannelInitializer() {
        return new ClientChannelInitializer();
    }

    /**
     * Connor 客户端
     * @param connorProperties
     * @param clientChannelInitializer
     * @return Client
     */
    @Bean
    public Client getClient(ConnorProperties connorProperties,
                            ClientChannelInitializer clientChannelInitializer) {
        return new Client(connorProperties,clientChannelInitializer);
    }
}
