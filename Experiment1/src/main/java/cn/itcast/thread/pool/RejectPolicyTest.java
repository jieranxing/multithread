package cn.itcast.thread.pool;

import java.util.concurrent.*;

public class RejectPolicyTest {
    public static void main(String[] args) {
        String[] policyNames = {"AbortPolicy", "CallerRunsPolicy", "DiscardOldestPolicy", "DiscardPolicy"};
        RejectedExecutionHandler[] handlers = {
                new ThreadPoolExecutor.AbortPolicy(),
                new ThreadPoolExecutor.CallerRunsPolicy(),
                new ThreadPoolExecutor.DiscardOldestPolicy(),
                new ThreadPoolExecutor.DiscardPolicy()
        };

        for (int i = 0; i < handlers.length; i++) {
            System.out.println("\n=== 测试策略：" + policyNames[i] + " ===");
            //1. 使用的线程池参数
            // 每次都新建线程池
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    2, 2,// corePoolSize 和 maximumPoolSize 都是2，核心线程数=最大线程数=2（只能开两个线程），说明线程池最大只能同时运行两个线程
                    60L, TimeUnit.SECONDS,// 空闲线程的存活时间
                    new ArrayBlockingQueue<>(2),// 队列长度为2
                    handlers[i]// 设置不同的拒绝策略
            );
/*
            总结：
            最大线程数 = 2，能马上运行 2 个任务。
            队列最大容量 = 2，还能缓冲 2 个任务。
            总共最多接受任务 = 2（线程）+2（队列）=4个
            剩下的 2 个任务提交时（从第5个任务开始），线程池+队列都满了 → 就会触发“拒绝策略”。
*/

            try {
                //2. 每种策略下执行6个任务
                for (int j = 1; j <= 6; j++) {
                    final int taskId = j;
                    executor.execute(() -> {
                        System.out.println(Thread.currentThread().getName() + " 正在处理任务 " + taskId);
                        try {
                            Thread.sleep(1000);//模拟耗时任务
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                }
            } catch (RejectedExecutionException e) {
                System.out.println("任务被拒绝：" + e.getMessage());
            }

            executor.shutdown();
            try {
                executor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }
}
