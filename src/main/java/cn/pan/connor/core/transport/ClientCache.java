package cn.pan.connor.core.transport;

import cn.hutool.core.util.StrUtil;
import cn.pan.connor.core.model.NewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 客户端缓存
 * @author Lucky Pan
 * @date 2022/4/18 16:59
 */
@Slf4j
public class ClientCache {

    /**
     * service-cache <service_name，service_list>
     */
    public static class ServiceCache {
        private static final String BLOCK_KEY = "service-list";
        private static final SynchronousQueue<String> SERVICES_BLOCK = new SynchronousQueue<>();
        private static final HashMap<String, List<NewService>> SERVICES = new HashMap<>();

        /**
         * 对server 响应的信息进行缓存
         */
        public static void cache(String serviceName,List<NewService> serviceList) {
            try {
                SERVICES_BLOCK.put(BLOCK_KEY);
            } catch (InterruptedException e) {
                ReflectionUtils.rethrowRuntimeException(e);
            }
            SERVICES.put(serviceName,serviceList);
        }

        /**
         * 从缓存获取service list
         */
        protected static List<NewService> getCache(String serviceName) {
            return SERVICES.get(serviceName);
        }

        /**
         * 当缓存中没有信息的时候阻塞获取信息
         */
        protected static List<NewService> block(String serviceName, int timeout) {
            try {
                if (Objects.isNull(SERVICES_BLOCK.poll(timeout, TimeUnit.MILLISECONDS))) {
                    throw new RuntimeException(StrUtil.format("discovery timeout {} ！",timeout));
                }
            } catch (InterruptedException e) {
                ReflectionUtils.rethrowRuntimeException(e);
            }
            return getCache(serviceName);
        }
    }

    public static class ServiceIdsCache {
        private static final String BLOCK_KEY = "service-id-list";
        private static final SynchronousQueue<String> SERVICE_IDS_BLOCK = new SynchronousQueue<>();
        private static final ArrayList<String> SERVICE_IDS = new ArrayList<>(16);

        /**
         * 对server 响应的信息进行缓存
         */
        public static void cache(List<String> serviceIds) {
            try {
                SERVICE_IDS_BLOCK.put(BLOCK_KEY);
            } catch (InterruptedException e) {
                ReflectionUtils.rethrowRuntimeException(e);
            }
            SERVICE_IDS.clear();
            SERVICE_IDS.addAll(serviceIds);
        }

        /**
         * 从缓存获取service id list
         */
        protected static List<String> getCache() {
            return SERVICE_IDS;
        }

        /**
         * 当缓存中没有信息的时候阻塞获取信息
         */
        protected static List<String> block(int timeout) {
            try {
                if (Objects.isNull(SERVICE_IDS_BLOCK.poll(timeout, TimeUnit.MILLISECONDS))) {
                    throw new RuntimeException(StrUtil.format("discovery timeout {} ！",timeout));
                }
            } catch (InterruptedException e) {
                ReflectionUtils.rethrowRuntimeException(e);
            }
            return getCache();
        }

    }


}
