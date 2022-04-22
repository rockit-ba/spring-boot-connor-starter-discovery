package cn.pan.connor.core.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 服务发现 获取所有的service names 响应
 * @author Lucky Pan
 * @date 2022/4/14 17:17
 */
@Slf4j
@Data
public class DiscoveryServiceNamesResponse {
    @SerializedName("service_names")
    private List<String> serviceNames;
}
