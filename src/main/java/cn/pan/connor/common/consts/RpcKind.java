package cn.pan.connor.common.consts;

/**
 * 请求/响应 的类型,任意一种类型都是成对出现的
 * @author Lucky Pan
 * @date 2022/4/14 17:38
 */
public interface RpcKind {
    /**
     * 服务注册
     */
    String REGISTRY = "0";
    /**
     * 服务发现:根据service-name 获取service list
     */
    String DISCOVERY = "1";

    /**
     * 服务发现:获取所有的service name
     */
    String DISCOVERY_NAMES = "2";

    /**
     * 服务下线
     */
    String DEREGISTRY = "3";

    /**
     * 服务检测
     */
    String SERVICE_CHECK = "4";

    /**
     * 通知客户端缓存添加某服务
     */
    String ADD_SERVICE = "5";
    /**
     * 通知客户端缓存删除某服务
     */
    String REMOVE_SERVICE = "6";

    /**
     * 心跳检测发送
     */
    String HEARTBEAT = "7";
}
