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
     * 服务发现:获取所有的service IDS
     */
    String DISCOVERY_IDS = "2";

    /**
     * 服务下线
     */
    String DEREGISTRY = "3";

    /**
     * 服务检测
     */
    String SERVICE_CHECK = "4";
}
