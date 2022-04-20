package cn.pan.connor;

import cn.hutool.core.lang.Assert;
import cn.pan.connor.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
//@SpringBootTest
class ConnorStarterApplicationTests {

	public record Entry(String name, Integer age) {
		public Entry {
			Assert.notBlank(name);
		}
	}

	@Test
	void contextLoads() {
		Entry entry = new Entry(null, 12);
		String s = JsonUtil.toStr(entry);
		System.out.println(s);
	}

}
