package cn.itcast.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        // 创建线程池，线程数量指定为3
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        // 计数器，记录任务执行次数
        AtomicInteger count = new AtomicInteger(0);
        int maxCount = 10;

        Runnable task = () -> {
            int current = count.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " 开始执行任务 " + current + "，时间：" + System.currentTimeMillis());

            try {
                Thread.sleep(2000); // 模拟任务耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (current >= maxCount) {
                System.out.println("任务已执行 " + maxCount + " 次，准备关闭线程池...");
                executor.shutdown();
            }
        };

        // 第一次延迟1秒执行，每次任务结束后延迟3秒再执行下一次
        executor.scheduleWithFixedDelay(task, 1, 3, TimeUnit.SECONDS);
    }
}
