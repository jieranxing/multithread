package cn.itcast.thread;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 创建多个任务
        Callable<String> task1 = new CallableWorker("任务A");
        Callable<String> task2 = new CallableWorker("任务B");
        Callable<String> task3 = new CallableWorker("任务C");

        try {
            // 提交任务并接收 Future 对象
            Future<String> future1 = executor.submit(task1);
            Future<String> future2 = executor.submit(task2);
            Future<String> future3 = executor.submit(task3);

            // 等待所有任务执行完毕并获取返回值
            System.out.println("结果1：" + future1.get());
            System.out.println("结果2：" + future2.get());
            System.out.println("结果3：" + future3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executor.shutdown();
        }

        System.out.println("主线程执行完毕！");
    }
}