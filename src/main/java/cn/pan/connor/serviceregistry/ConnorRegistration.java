package cn.pan.connor.serviceregistry;

import cn.pan.connor.core.conf.ConnorDiscoveryProperties;
import cn.pan.connor.core.conf.Discovery;
import cn.pan.connor.core.model.NewService;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.serviceregistry.Registration;

import java.net.URI;
import java.util.Map;

/**
 * 注册服务实现类
 * @author Lucky Pan
 * @date 2022/4/17 15:51
 */

public class ConnorRegistration implements Registration {
    private final NewService service;
    private final Discovery discovery;

    public ConnorRegistration(ConnorDiscoveryProperties properties) {
        this.discovery = properties.getDiscovery();
        this.service = NewService.fromProperties(properties);
    }

    public NewService getService() {
        return this.service;
    }

    @Override
    public String getInstanceId() {
        return getService().getId();
    }

    @Override
    public String getServiceId() {
        return getService().getName();
    }

    @Override
    public String getHost() {
        return getService().getHost();
    }

    @Override
    public int getPort() {
        return getService().getPort();
    }

    @Override
    public boolean isSecure() {
        return discovery.isSecure();
    }

    @Override
    public URI getUri() {
        return DefaultServiceInstance.getUri(this);
    }

    @Override
    public Map<String, String> getMetadata() {
        return getService().getMeta();
    }
}
