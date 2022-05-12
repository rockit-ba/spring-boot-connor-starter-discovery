package cn.pan.connor.core.model.response;

import cn.pan.connor.core.model.NewService;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author pan
 * @create 2022/4/29 5:05 下午
 * <p>
 *  本地添加对应的服务
 * </p>
 */
@Data
public class AddServiceResponse {
    @SerializedName("service_name")
    private String serviceName;
    @SerializedName("service_list")
    private List<NewService> serviceList;
}
