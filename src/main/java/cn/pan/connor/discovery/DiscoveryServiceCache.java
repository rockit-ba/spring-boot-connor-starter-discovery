package cn.pan.connor.discovery;

import cn.pan.connor.core.model.NewService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Lucky Pan
 * @date 2022/4/18 16:59
 */
@Slf4j
public class DiscoveryServiceCache {
    private static final SynchronousQueue<String> QUEUE = new SynchronousQueue<>();
    private static final HashMap<String, List<NewService>> SERVICES = new HashMap<>();
    private static final ArrayList<String> SERVICE_IDS = new ArrayList<>(16);

    /**
     * 获取service-list
     */
    protected static List<NewService> getServiceList(String serviceName) {
        try {
            QUEUE.poll(150, TimeUnit.MILLISECONDS);
            return getCacheServiceList(serviceName);
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        throw new RuntimeException("discovery timeout ！");
    }

    /**
     * 获取service-ids
     */
    protected static List<String> getServiceIds() {
        try {
            QUEUE.poll(150, TimeUnit.MILLISECONDS);
            return getCacheServiceIds();
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        throw new RuntimeException("discovery timeout ！");
    }

    /**
     * 将发现的服务进行本地缓存
     */
    public static void cacheServiceList(String serviceName, List<NewService> services) {
        try {
            QUEUE.put(serviceName);
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        DiscoveryServiceCache.SERVICES.put(serviceName, services);
    }

    /**
     * 缓存service-ids
     */
    public static void cacheServiceIds(List<String> services) {
        try {
            QUEUE.put("services");
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        SERVICE_IDS.addAll(services);
    }

    /**
     * 获取本地缓存的 service-list
     */
    protected static List<NewService> getCacheServiceList(String serviceName) {
        return SERVICES.get(serviceName);
    }

    /**
     * 获取本地缓存的service-ids
     */
    protected static List<String> getCacheServiceIds() {
        return SERVICE_IDS;
    }


}
