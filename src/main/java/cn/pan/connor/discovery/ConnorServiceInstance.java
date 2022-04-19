package cn.pan.connor.discovery;

import cn.pan.connor.core.model.NewService;
import org.springframework.cloud.client.DefaultServiceInstance;

/**
 * @author Lucky Pan
 * @date 2022/4/18 16:22
 */
public class ConnorServiceInstance extends DefaultServiceInstance {

    public ConnorServiceInstance(NewService newService) {
        super(newService.getId(), newService.getName(),
                newService.getHost(), newService.getPort(),
                false, newService.getMeta());
    }
}
