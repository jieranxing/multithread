package cn.itcast.thread.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExtendedThreadPoolExecutorTest {

    public static void main(String[] args) {
        // 创建自定义线程池
        ThreadPoolExecutor executor = new MyThreadPoolExecutor(
                2, 4,                         // 核心线程数2，最大线程数4
                60L, TimeUnit.SECONDS,       // 空闲线程存活时间
                new LinkedBlockingQueue<>(), // 使用无界队列
                Executors.defaultThreadFactory()
        );

        // 提交几个简单任务
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000); // 模拟耗时操作
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " 正在执行任务 " + taskId);
            });
        }

        // 关闭线程池
        executor.shutdown();
    }

    // 自定义线程池类，继承ThreadPoolExecutor
    static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        private final AtomicInteger taskCount = new AtomicInteger(0);

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                    long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue,
                                    ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        // 每个任务开始前执行
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            System.out.println("[beforeExecute] 线程 " + t.getName() + " 即将开始任务");
        }

        // 每个任务执行完后执行
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            System.out.println("[afterExecute] 一个任务已执行完毕");
            if (t != null) {
                System.out.println("[afterExecute] 任务异常：" + t.getMessage());
            }
            taskCount.incrementAndGet();
        }

        // 线程池完全终止后执行
        @Override
        protected void terminated() {
            super.terminated();
            System.out.println("[terminated] 所有任务执行完成，线程池已终止，总共执行了 " + taskCount.get() + " 个任务。");
        }
    }
}
