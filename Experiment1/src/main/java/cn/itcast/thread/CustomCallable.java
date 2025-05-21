package cn.itcast.thread;

import java.util.concurrent.Callable;

public class CustomCallable implements Callable<String> {
    public String call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " 执行任务：" + i);
            Thread.sleep(500);  // 模拟子线程耗时任务
        }
        return "执行完成，返回结果: " + System.currentTimeMillis();
    }
}