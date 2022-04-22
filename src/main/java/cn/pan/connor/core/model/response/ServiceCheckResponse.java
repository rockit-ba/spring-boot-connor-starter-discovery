package cn.pan.connor.core.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务健康检查请求
 *
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
@Data
public class ServiceCheckResponse{

    @SerializedName("service_id")
    private String serviceId;
}
