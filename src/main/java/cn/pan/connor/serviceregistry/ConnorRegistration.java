package cn.pan.connor.serviceregistry;

import cn.pan.connor.conf.ConnorDiscoveryProperties;
import cn.pan.connor.model.NewService;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Map;

/**
 * @author Lucky Pan
 * @date 2022/4/17 15:51
 */

public class ConnorRegistration implements Registration {

    private final NewService service;

    private final ConnorDiscoveryProperties properties;

    public ConnorRegistration(NewService service,
                              ConnorDiscoveryProperties properties) {
        this.service = service;
        this.properties = properties;
    }


    @Override
    public String getServiceId() {
        return null;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public URI getUri() {
        return null;
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }
}
