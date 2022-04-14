package cn.pan.connor.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 *     数据传输实体
 * </p>
 * @author jixinag
 * @date 2022/4/14
 */
@Data
@AllArgsConstructor
public class Entry {
	private String name;
	private Integer age;
}