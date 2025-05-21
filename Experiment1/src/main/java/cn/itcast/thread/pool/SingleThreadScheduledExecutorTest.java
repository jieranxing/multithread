package cn.itcast.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SingleThreadScheduledExecutorTest {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // 计数器，用于统计任务执行次数
        AtomicInteger count = new AtomicInteger(0);
        int maxCount = 10; // 最多执行10次

        Runnable task = () -> {
            int current = count.incrementAndGet();
            System.out.println(Thread.currentThread().getName() + " 执行任务 - 次数: " + current + " - 时间: " + System.currentTimeMillis());

            // 达到指定次数后关闭线程池
            if (current >= maxCount) {
                System.out.println("任务已执行 " + maxCount + " 次，准备关闭线程池...");
                executor.shutdown(); // 关闭调度器
            }
        };

        // scheduleWithFixedDelay：任务之间的间隔是“任务结束后等待多久再执行下一次”
        executor.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS);
    }
}
