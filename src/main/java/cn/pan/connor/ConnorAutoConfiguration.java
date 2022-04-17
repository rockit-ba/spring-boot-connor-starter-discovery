package cn.pan.connor;

import cn.pan.connor.conf.ConnorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * connor client 自动装配
 * @author Lucky Pan
 * @date 2022/4/13 20:47
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ConnorProperties.class)
@AutoConfigureAfter({ConfigurationPropertiesAutoConfiguration.class})
@ComponentScan({"cn.pan.connor","org.springframework.cloud"})
public class ConnorAutoConfiguration {
    public ConnorAutoConfiguration() {}

}
