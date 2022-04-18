package cn.pan.connor.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Lucky Pan
 * @date 2022/4/17 16:26
 */
@Data
@Builder
@NoArgsConstructor
public class NewService {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("address")
    private String address;
    @SerializedName("meta")
    private Map<String, String> meta;
    @SerializedName("port")
    private Integer port;
}
