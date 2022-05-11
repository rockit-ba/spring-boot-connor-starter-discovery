package cn.pan.connor.core.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 心跳超时响应
 * </p>
 *
 * @author pan
 * @date 2022/5/11 11:34 上午
 */
@Data
public class HeartbeatTimeoutResponse {
    @SerializedName("service_ids")
    private List<String> serviceIds;
}
