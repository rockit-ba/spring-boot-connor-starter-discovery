package cn.pan.connor.core.transport;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *      心跳检测定时任务
 * </p>
 *
 * @author pan
 * @date 2022/5/4 3:34 下午
 */
@Slf4j
public class HeartbeatSchedule {

    public HeartbeatSchedule(ConnorClient client) {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1, r -> {
            Thread thread = new Thread(r, "heartbeat");
            thread.setDaemon(true);
            return thread;
        });
        log.info("HeartbeatSchedule init");
        poolExecutor.schedule(client::doHeartbeat,30, TimeUnit.SECONDS);
    }
}
