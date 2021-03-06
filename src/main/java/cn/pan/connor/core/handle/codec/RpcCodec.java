package cn.pan.connor.core.handle.codec;

/**
 * <p>
 *     请求响应公共方法
 * </p>
 * @author jixinag
 * @date 2022/4/16
 */
public interface RpcCodec {
    /**
     * 像服务端发送的 请求类型
     * @return type
     */
    String getRpcKind();
}
