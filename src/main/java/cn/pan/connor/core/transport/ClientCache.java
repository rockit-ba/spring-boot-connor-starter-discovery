package cn.pan.connor.core.transport;

import cn.hutool.core.util.StrUtil;
import cn.pan.connor.core.model.NewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        private static final ConcurrentSkipListMap<String, List<NewService>> SERVICES = new ConcurrentSkipListMap<>();

        /**
         * 根据server的推送，移除超时的实例
         * @param timeoutServiceIds 超时的实例id
         */
        public static void timeoutUpdate(List<String> timeoutServiceIds) {
            SERVICES.forEach((k,v) -> {
                List<NewService> services = v.stream()
                        .filter(service -> !timeoutServiceIds.contains(service.getId()))
                        .collect(Collectors.toList());
                SERVICES.put(k,services);
            });
        }

        /**
         * 根据server的推送更新本地缓存
         */
        public static void update(String serviceName,List<NewService> serviceList) {
            SERVICES.put(serviceName,serviceList);
        }

        /**
         * 对server 响应的信息进行缓存,次动作只能是客户端主动请求服务端，然后服务端响应
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

    public static class ServiceNamesCache {
        private static final String BLOCK_KEY = "service-name-list";
        private static final SynchronousQueue<String> SERVICE_NAMES_BLOCK = new SynchronousQueue<>();
        private static final ArrayList<String> SERVICE_NAMES = new ArrayList<>(16);

        /**
         * 正对server端的主动推送，更新本地缓存
         */
        public static synchronized void update(List<String> serviceNames) {
            SERVICE_NAMES.clear();
            SERVICE_NAMES.addAll(serviceNames);
        }

        /**
         * 对server 响应的信息进行缓存，正对客户端主动请求server的响应的设置
         */
        public static void cache(List<String> serviceNames) {
            try {
                SERVICE_NAMES_BLOCK.put(BLOCK_KEY);
            } catch (InterruptedException e) {
                ReflectionUtils.rethrowRuntimeException(e);
            }
            SERVICE_NAMES.clear();
            SERVICE_NAMES.addAll(serviceNames);
        }

        /**
         * 从缓存获取service name list
         */
        protected static List<String> getCache() {
            return SERVICE_NAMES;
        }

        /**
         * 当缓存中没有信息的时候阻塞获取信息
         */
        protected static List<String> block(int timeout) {
            try {
                if (Objects.isNull(SERVICE_NAMES_BLOCK.poll(timeout, TimeUnit.MILLISECONDS))) {
                    throw new RuntimeException(StrUtil.format("discovery timeout {} ！",timeout));
                }
            } catch (InterruptedException e) {
                ReflectionUtils.rethrowRuntimeException(e);
            }
            return getCache();
        }

    }

    /**
     * 服务状态检查
     */
    public static class ServiceCheck {
        private static final SynchronousQueue<String> SERVICE_CHECK_BLOCK = new SynchronousQueue<>();

        /**
         * 设置服务端响应的数据
         */
        public static void putResp(String serviceId) {
            try {
                SERVICE_CHECK_BLOCK.put(serviceId);
            } catch (InterruptedException e) {
                ReflectionUtils.rethrowRuntimeException(e);
            }
        }

        /**
         * 同步获取响应的结果
         * @return 查询的服务的id
         */
        protected static String block(int timeout) {
            String msg = "";
            try {
                msg = SERVICE_CHECK_BLOCK.poll(timeout, TimeUnit.MILLISECONDS);
                if (Objects.isNull(msg)) {
                    throw new RuntimeException(StrUtil.format("check timeout {} ！",timeout));
                }
            } catch (InterruptedException e) {
                ReflectionUtils.rethrowRuntimeException(e);
            }
            return msg;
        }
    }

}
