package cn.pan.connor.common.consts;

/**
 * 请求响应的类型
 * @author Lucky Pan
 * @date 2022/4/14 17:38
 */
public interface RpcKind {
    /**
     * 服务注册
     */
    String REGISTRY = "0";
    /**
     * 服务发现
     */
    String DISCOVERY = "1";
}
