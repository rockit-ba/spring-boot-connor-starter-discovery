package cn.pan.connor.serviceregistry;

import cn.pan.connor.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.event.InstancePreRegisteredEvent;
import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;

/**
 * 服务注册后的事件监听
 * @author Lucky Pan
 * @date 2022/4/17 15:54
 */
@Slf4j
public class InstanceRegisteredEventListener
        implements ApplicationListener<InstanceRegisteredEvent>
{
    @Override
    public void onApplicationEvent(InstanceRegisteredEvent event) {
        log.info("InstanceRegisteredEventListener:{}", JsonUtil.toStr(event));
    }
}
