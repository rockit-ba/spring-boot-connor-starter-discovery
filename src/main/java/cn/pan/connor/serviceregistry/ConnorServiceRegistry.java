package cn.pan.connor.serviceregistry;

import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.transport.ConnorClient;
import cn.pan.connor.core.model.request.RegistryRequest;
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
               log.info("Service register send success：{}",registryRequest.newService().getName());
           }
        });
    }

    /**
     * 取消注册
     * @param registration {@link ConnorRegistration}
     */
    @Override
    public void deregister(ConnorRegistration registration) {
        log.info("Service deregister：{}",JsonUtil.toStr(registration.getService().getName()));
    }

    @Override
    public void close() {
        log.info("Service close");
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
