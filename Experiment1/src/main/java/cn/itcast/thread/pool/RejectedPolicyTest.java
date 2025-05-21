package cn.itcast.thread.pool;

import java.util.concurrent.*;
//5. 拒绝策略示例（AbortPolicy）
public class RejectedPolicyTest {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(
                2, 4, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略：直接抛出异常
        );

        for (int i = 0; i < 10; i++) {
            final int taskNum = i;
            try {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 执行任务 " + taskNum);
                });
            } catch (RejectedExecutionException e) {
                System.out.println("任务 " + taskNum + " 被拒绝执行！");
            }
        }

        pool.shutdown();
    }
}
