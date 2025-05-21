package cn.itcast.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//2. 单线程池示例（SingleThreadExecutor）
public class SingleThreadExecutorTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 执行任务");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();
    }
}
