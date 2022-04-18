package cn.pan.connor.serviceregistry;

import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

/**
 * @author Lucky Pan
 * @date 2022/4/17 15:48
 */
public class ConnorServiceRegistry implements ServiceRegistry<ConnorRegistration> {
    @Override
    public void register(ConnorRegistration registration) {

    }

    @Override
    public void deregister(ConnorRegistration registration) {

    }

    @Override
    public void close() {

    }

    @Override
    public void setStatus(ConnorRegistration registration, String status) {

    }

    @Override
    public <T> T getStatus(ConnorRegistration registration) {
        return null;
    }
}
