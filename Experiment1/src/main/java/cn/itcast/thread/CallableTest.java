package cn.itcast.thread;

import java.util.concurrent.*;

public class CallableTest{
    public static void main(String[] args) {
        Callable<String> callableTask = new CustomCallable();
        FutureTask<String> futureTask = new FutureTask<>(callableTask);
        Thread thread = new Thread(futureTask, "CallableThread");
        thread.start();

        // 主线程
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " mainThread 执行任务：" + i);
            try {
                Thread.sleep(300); // 主线程同样耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            String result = futureTask.get(); // 等待子线程结果，调用Future Task. get（）方法
            System.out.println("子线程返回结果：" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}