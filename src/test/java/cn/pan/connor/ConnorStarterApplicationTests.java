package cn.pan.connor;

import cn.hutool.core.date.DateUtil;
import cn.pan.connor.core.model.NewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

	@Test
	void test_01() {
		ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1, r -> {
			Thread thread = new Thread(r, "heartbeat");
			thread.setDaemon(true);
			return thread;
		});
		poolExecutor.scheduleAtFixedRate(() -> {
			System.out.println(DateUtil.now());
		}, 5, 5, TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static  ConcurrentSkipListMap<String, List<NewService>> SERVICES = new ConcurrentSkipListMap<>();

	public static void timeoutUpdate(List<String> serviceIds) {
		SERVICES.forEach((k,v) -> {
			List<NewService> services = v.stream()
					.filter(service -> !serviceIds.contains(service.getId()))
					.collect(Collectors.toList());
			SERVICES.put(k,services);
		});
	}

	@Test
	void test_02() {
		// 为SERVICES初始化数据
		SERVICES.put("1",
				List.of(
						NewService.builder().id("1").build(),
						NewService.builder().id("2").build(),
						NewService.builder().id("3").build()));
		SERVICES.put("2",
				List.of(
						NewService.builder().id("1").build(),
						NewService.builder().id("2").build(),
						NewService.builder().id("3").build()));
		// 创建一个ID list
		List<String> serviceIds = List.of("2");
		// 调用timeoutUpdate方法
		timeoutUpdate(serviceIds);
		// 打印结果
		SERVICES.forEach((k,v) -> {
			System.out.println(k + ":" + v);
		});

	}


}
