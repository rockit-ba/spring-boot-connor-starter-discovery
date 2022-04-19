package cn.pan.connor.discovery;

import cn.pan.connor.core.conf.ConnorDiscoveryProperties;
import cn.pan.connor.core.model.NewService;
import cn.pan.connor.core.transport.ConnorClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.ArrayList;
import java.util.List;

/**
 * DiscoveryClient 实现类
 * @author Lucky Pan
 * @date 2022/4/18 12:02
 */
public class ConnorDiscoveryClient implements DiscoveryClient {
    private final ConnorClient client;
    private final ConnorDiscoveryProperties properties;

    public ConnorDiscoveryClient(ConnorDiscoveryProperties properties,
                                 ConnorClient client) {
        this.properties = properties;
        this.client = client;
    }

    @Override
    public String description() {
        return "ConnorDiscoveryClient";
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceName) {
        List<ServiceInstance> instances = new ArrayList<>();

        this.client.asyncGetService(serviceName);
        final List<NewService> services = DiscoveryServiceQueue.getService(serviceName);
        services.forEach(ele -> {
            instances.add(new ConnorServiceInstance(ele));
        });

        return instances;

    }

    @Override
    public List<String> getServices() {
        this.client.asyncGetAllServiceIds();
        return DiscoveryServiceQueue.getServiceIds();
    }
}
