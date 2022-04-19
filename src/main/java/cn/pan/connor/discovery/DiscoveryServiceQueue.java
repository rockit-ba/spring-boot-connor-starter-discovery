package cn.pan.connor.discovery;

import cn.pan.connor.core.model.NewService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Lucky Pan
 * @date 2022/4/18 16:59
 */
public class DiscoveryServiceQueue {
    private static final SynchronousQueue<String> QUEUE = new SynchronousQueue<>();
    private static final HashMap<String, List<NewService>> SERVICES = new HashMap<>();
    private static final ArrayList<String> SERVICE_IDS = new ArrayList<>(16);

    public static void addService(String serviceName, List<NewService> services) {
        try {
            QUEUE.put(serviceName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DiscoveryServiceQueue.SERVICES.put(serviceName, services);
    }

    public static List<NewService> getService(String serviceName) {
        try {
            QUEUE.poll(150, TimeUnit.MILLISECONDS);
            return SERVICES.get(serviceName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("discovery timeout ！");
    }

    public static void addServices(List<String> services) {
        try {
            QUEUE.put("services");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SERVICE_IDS.addAll(services);
    }

    public static List<String> getServiceIds() {
        try {
            QUEUE.poll(150, TimeUnit.MILLISECONDS);
            return SERVICE_IDS;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("discovery timeout ！");
    }
}
