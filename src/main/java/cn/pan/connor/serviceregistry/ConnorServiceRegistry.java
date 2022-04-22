package cn.pan.connor.serviceregistry;

import cn.hutool.core.util.StrUtil;
import cn.pan.connor.common.utils.JsonUtil;
import cn.pan.connor.core.model.request.DeregistryRequest;
import cn.pan.connor.core.model.request.RegistryRequest;
import cn.pan.connor.core.transport.ConnorClient;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

import static org.springframework.boot.actuate.health.Status.*;

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
                new DeregistryRequest(registration.getServiceId(), registration.getInstanceId());
        connorClient.send(deregistryRequest);
    }

    @Override
    public void close() {
        connorClient.sourceClose();
    }

    @Override
    public void setStatus(ConnorRegistration registration, String status) {
        log.info("Registry setStatus：{}",status);
        // 服务退出/下线（与down的状态有所区别，down是机器自己不可用，OUT_OF_SERVICE 是认为将服务下线）
        if (status.equalsIgnoreCase(OUT_OF_SERVICE.getCode())) {
            this.deregister(registration);
        }
        else if (status.equalsIgnoreCase(UP.getCode())) {
            this.register(registration);
        }
        else {
            throw new IllegalArgumentException("Unknown status: " + status);
        }

    }

    /**
     * 获取状态，如果Connor server 返回的service id 不为空则正常，否则不正常
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object getStatus(ConnorRegistration registration) {
        String check = this.connorClient.serviceCheck(registration.getInstanceId());
        if (StrUtil.isBlank(check)) {
            return OUT_OF_SERVICE.getCode();
        }
        return UP.getCode();
    }

}
