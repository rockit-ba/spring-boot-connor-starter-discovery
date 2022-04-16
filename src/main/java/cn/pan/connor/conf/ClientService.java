package cn.pan.connor.conf;

import lombok.Data;

import java.util.HashMap;

/**
 * <p>
 *     客户端服务的信息
 * </p>
 * @author jixinag
 * @date 2022/4/16
 */
@Data
public class ClientService {
    /**
     * client service port.
     */
    private Integer port = 8080;
    /**
     * client service host.
     */
    private String host = "localhost";

    /**
     * client service custom data
     */
    private HashMap<String,String> meta = new HashMap<>();
}