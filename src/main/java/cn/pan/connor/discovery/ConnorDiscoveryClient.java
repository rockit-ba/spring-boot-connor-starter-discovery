package cn.pan.connor.discovery;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DiscoveryClient 实现类
 * @author Lucky Pan
 * @date 2022/4/18 12:02
 */
public class ConnorDiscoveryClient implements DiscoveryClient {

    private final List<ServiceInstance> serviceInstances;

    public ConnorDiscoveryClient(List<ServiceInstance> serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    @Override
    public String description() {
        return "ConnorDiscoveryClient";
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        return serviceInstances.stream()
                .filter(ele -> ele.getServiceId().equals(serviceId))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getServices() {
        return serviceInstances.stream()
                .map(ServiceInstance::getServiceId)
                .collect(Collectors.toList());
    }
}
