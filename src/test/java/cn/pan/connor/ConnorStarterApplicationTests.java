package cn.pan.connor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
//@SpringBootTest
class ConnorStarterApplicationTests {

	@Data
	public static class Entry {
		@SerializedName("my_name")
		private String myName;
		private Integer age;
	}

	@Test
	void contextLoads() {
		Entry entry = new Entry();
		entry.setAge(23);
		entry.setMyName("蕾姆");
		Gson gson = new Gson();
		String s = gson.toJson(entry);
		log.info(s);

		Entry entry1 = gson.fromJson("{\"my_name\":\"蕾姆\",\"age\":23}", Entry.class);
		log.info(entry1.toString());
	}

}
