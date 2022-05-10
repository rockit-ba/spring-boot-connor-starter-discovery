package cn.pan.connor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
//@SpringBootTest
class ConnorStarterApplicationTests {

	@Test
	void contextLoads() {
		SynchronousQueue<String> QUEUE = new SynchronousQueue<>();
		try {
			if (Objects.isNull(QUEUE.poll(5000, TimeUnit.MILLISECONDS))) {
				throw new RuntimeException("discovery timeout ！");
			}
		} catch (InterruptedException e) {
			log.error(e.getMessage(),e);
		}

		System.out.println("poll");
	}

}
