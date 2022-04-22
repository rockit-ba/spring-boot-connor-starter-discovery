package cn.pan.connor.discovery;

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

    public ConnorDiscoveryClient(ConnorClient client) {
        this.client = client;
    }

    @Override
    public String description() {
        return "ConnorDiscoveryClient";
    }

    /**
     * 获取某个service的所有的实例
     */
    @Override
    public List<ServiceInstance> getInstances(String serviceName) {
        List<ServiceInstance> instances = new ArrayList<>();

        final List<NewService> services = this.client.getService(serviceName);
        services.forEach(ele -> instances.add(new ConnorServiceInstance(ele)));

        return instances;

    }

    /**
     * 获取所有的存在的 service name
     */
    @Override
    public List<String> getServices() {
        return this.client.getServiceNameList();
    }
}
