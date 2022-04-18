package cn.pan.connor;

import cn.pan.connor.serviceregistry.InstancePreRegisteredEventListener;
import cn.pan.connor.serviceregistry.InstanceRegisteredEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * listener 配置
 * @author Lucky Pan
 * @date 2022/4/18 10:43
 */
@Configuration
public class ListenerConfiguration {
    @Bean
    public InstancePreRegisteredEventListener instancePreRegisteredEventListener() {
        return new InstancePreRegisteredEventListener();
    }

    @Bean
    public InstanceRegisteredEventListener instanceRegisteredEventListener() {
        return new InstanceRegisteredEventListener();
    }

}
