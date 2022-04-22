package cn.pan.connor.serviceregistry;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.request.DeregistryRequest;
import cn.pan.connor.core.model.request.RegistryRequest;
import cn.pan.connor.core.transport.ConnorClient;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

/**
 * 服务注册实现类
 * @author Lucky Pan
 * @date 2022/4/17 15:48
 */
@Slf4j
public class ConnorServiceRegistry implements ServiceRegistry<ConnorRegistration> {
    @Autowired
    private ConnorClient connorClient;

    /**
     * 注册服务
     * @param registration {@link ConnorRegistration}
     */
    @Override
    public void register(ConnorRegistration registration) {
        RegistryRequest registryRequest = new RegistryRequest(registration.getService());
        ChannelFuture future = connorClient.send(registryRequest);
        future.addListener(ele -> {
           if (future.isSuccess()) {
               log.info("Registering service with consul: " + registration.getService());
           }
        });
    }

    /**
     * 取消注册
     * @param registration {@link ConnorRegistration}
     */
    @Override
    public void deregister(ConnorRegistration registration) {
        log.info("Service deregister：{}",JsonUtil.toStr(registration.getServiceId()));
        DeregistryRequest deregistryRequest =
                new DeregistryRequest(registration.getService().getName(),registration.getServiceId());
        connorClient.send(deregistryRequest);
    }

    @Override
    public void close() {
        connorClient.sourceClose();
    }

    @Override
    public void setStatus(ConnorRegistration registration, String status) {
        log.info("Registry setStatus：{}",status);

    }

    @Override
    public <T> T getStatus(ConnorRegistration registration) {
        log.info("Segistry getStatus：{}",registration);
        return null;
    }

}
