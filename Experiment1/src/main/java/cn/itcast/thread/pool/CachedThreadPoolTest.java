/*
package cn.itcast.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//3. 可缓存线程池（CachedThreadPool）
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

// 第1轮：提交10个任务
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing task (1st round)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            System.out.println("Waiting 70 seconds to trigger thread cleanup...");
            // 等待超过60秒，触发空闲线程被回收
            Thread.sleep(70000); // 等待超过60秒，使线程被销毁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Submitting 2nd round of tasks...");

// 第2轮：再次提交3个新任务
        for (int i = 0; i < 3; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing task (2nd round)");
            });
        }

        pool.shutdown();


    }
}
*/

package cn.itcast.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        // Create a cached thread pool
        ExecutorService pool = Executors.newCachedThreadPool();

        // First round of tasks
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing task (1st round)");
            });
        }

        // Sleep for 30 seconds (< 60 seconds, threads are still alive)
        System.out.println("Waiting 30 seconds before submitting the 2nd round of tasks...");
        Thread.sleep(30000);

        // Second round of tasks
        System.out.println("Submitting 2nd round of tasks...");
        for (int i = 0; i < 3; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing task (2nd round)");
            });
        }

        // Shutdown the pool
        pool.shutdown();
    }
}
