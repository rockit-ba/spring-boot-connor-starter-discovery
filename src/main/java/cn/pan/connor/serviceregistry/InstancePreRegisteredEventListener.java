package cn.pan.connor.serviceregistry;

import org.springframework.cloud.client.discovery.event.InstancePreRegisteredEvent;
import org.springframework.context.ApplicationListener;

/**
 * 服务注册前的事件监听
 * @author Lucky Pan
 * @date 2022/4/17 15:54
 */
public class InstancePreRegisteredEventListener
        implements ApplicationListener<InstancePreRegisteredEvent>
{
    @Override
    public void onApplicationEvent(InstancePreRegisteredEvent event) {

    }
}
