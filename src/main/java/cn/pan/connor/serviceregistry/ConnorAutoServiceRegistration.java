package cn.pan.connor.serviceregistry;

import cn.pan.connor.core.conf.ConnorDiscoveryProperties;
import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

/**
 * 自定义服务注册
 * @author Lucky Pan
 * @date 2022/4/18 13:27
 */
public class ConnorAutoServiceRegistration
        extends AbstractAutoServiceRegistration<ConnorRegistration>
{

    private final ConnorRegistration registration;
    private final ConnorDiscoveryProperties properties;

    public ConnorAutoServiceRegistration(ServiceRegistry<ConnorRegistration> serviceRegistry,
                                         AutoServiceRegistrationProperties autoServiceRegistrationProperties,
                                         ConnorRegistration registration,
                                         ConnorDiscoveryProperties properties)
    {
        super(serviceRegistry, autoServiceRegistrationProperties);
        this.properties = properties;
        this.registration = registration;
    }

    @Deprecated
    @Override
    protected Object getConfiguration() {
        return this.properties;
    }

    @Override
    public void start() {
        super.start();
    }

    /**
     * 当服务startup的时候会触发调用
     */
    @Override
    protected void register() {
        super.register();
    }

    /**
     * 当服务stop的时候会触发调用
     */
    @Override
    protected void deregister() {
        super.deregister();
    }

    @Override
    protected boolean isEnabled() {
        // todo
        return true;
    }

    @Override
    protected ConnorRegistration getRegistration() {
        return this.registration;
    }

    @Override
    protected ConnorRegistration getManagementRegistration() {
        // todo
        return this.registration;
    }
}
