package cn.pan.connor.listener;

import cn.pan.connor.transport.Client;
import cn.pan.connor.transport.request.RegistryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     服务启动监听
 * </p>
 * @author jixinag
 * @date 2022/4/16
 */
@Slf4j
@Component
public class ApplicationStartupListener implements ApplicationRunner {
    @Autowired
    private Client client;
    @Autowired
    private RegistryRequest registryRequest;

    @Override
    public void run(ApplicationArguments args) {
        serviceRegistry();
    }

    /**
     * 发起服务注册
     */
    private void serviceRegistry() {
        // 发起服务注册
        client.send(registryRequest).addListener(ele -> {
            if (ele.isSuccess()) {
                log.info("服务注册信息已发送");
            }
        });
    }
}