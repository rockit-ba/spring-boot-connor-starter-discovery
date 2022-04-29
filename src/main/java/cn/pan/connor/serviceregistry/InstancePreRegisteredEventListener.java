package cn.pan.connor.serviceregistry;

import cn.pan.connor.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.event.InstancePreRegisteredEvent;
import org.springframework.context.ApplicationListener;

/**
 * 服务注册前的事件监听
 * @author Lucky Pan
 * @date 2022/4/17 15:54
 */
@Slf4j
public class InstancePreRegisteredEventListener
        implements ApplicationListener<InstancePreRegisteredEvent>
{


    @Override
    public void onApplicationEvent(InstancePreRegisteredEvent event) {
        log.info("InstancePreRegisteredEventListener:{}", JsonUtil.toStr(event));
    }
}
