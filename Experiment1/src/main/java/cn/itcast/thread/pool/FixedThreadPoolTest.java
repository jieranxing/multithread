package cn.itcast.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//1. 固定线程池示例（FixedThreadPool）
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        //Create a thread pool with a fixed number of 3 threads
        ExecutorService pool = Executors.newFixedThreadPool(3);

        //submit 10 tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                //Print the name of the current thread executing this task
                System.out.println(Thread.currentThread().getName() + "正在执行任务");
                try {
                    //Simulate task processing time with 1 second delay
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //Handle possible interruption during sleep
                    e.printStackTrace();
                }
            });
        }

        //Initiate an orderly shutdown of the thread pool
        //No new tasks will be accepted, but previously submitted tasks will complete
        pool.shutdown(); // 关闭线程池
    }
}
