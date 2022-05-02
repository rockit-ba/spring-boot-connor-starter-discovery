package cn.pan.connor.core.model.response;

import cn.pan.connor.core.model.NewService;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author pan
 * @create 2022/4/29 5:06 下午
 * <p>
 *  从本地移除指定服务
 * </p>
 */
@Data
public class RemoveServiceResponse {
    @SerializedName("service_name")
    private String serviceName;
    @SerializedName("service_list")
    private List<NewService> serviceList;
}
