package cn.pan.connor.codec;

/**
 * <p>
 *     请求响应公共方法
 * </p>
 * @author jixinag
 * @date 2022/4/16
 */
public interface RpcCodec {
    /**
     * 获取请求类型
     * @return type
     */
    String getRpcKind();
}
