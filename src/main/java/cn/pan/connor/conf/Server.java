package cn.pan.connor.conf;

import lombok.Data;

/**
 * <p>
 *     connor 服务端的信息
 * </p>
 * @author jixinag
 * @date 2022/4/16
 */
@Data
public class Server {
    /**
     * connor server port.
     */
    private Integer port = 8080;
    /**
     * connor server host.
     */
    private String connect = "localhost";
}