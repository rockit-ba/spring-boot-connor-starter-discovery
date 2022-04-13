package cn.pan.connorstarter.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Lucky Pan
 * @Date 2022/4/13 20:47
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ConnorProperties.class)
public class ConnorAutoConfiguration implements InitializingBean {
    private final ConnorProperties connorProperties;

    public ConnorAutoConfiguration(ConnorProperties connorProperties) {
        log.info("配置文件加载：{}",connorProperties);
        this.connorProperties = connorProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkConfigFileExists();
    }
    private void checkConfigFileExists() {
        // 可以做一些后续的事情
    }
}
