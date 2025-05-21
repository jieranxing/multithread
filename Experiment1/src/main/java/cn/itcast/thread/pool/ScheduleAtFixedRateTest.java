package cn.itcast.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduleAtFixedRateTest {
    public static void main(String[] args) {
        // 创建一个大小为2的计划任务线程池
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        AtomicInteger count = new AtomicInteger(0);
        int maxCount = 10;

        Runnable task = () -> {
            int current = count.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " 开始执行任务 " + current + "，时间：" + System.currentTimeMillis());
            try {
                Thread.sleep(3000); // 模拟任务执行3秒
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (current >= maxCount) {
                System.out.println("任务已执行 " + maxCount + " 次，准备关闭线程池...");
                executor.shutdown();
            }
        };

        // 初始延迟1秒，每2秒调度一次任务（不管上次任务是否完成）
        executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        // 等待线程池任务执行完毕
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
