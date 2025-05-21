package cn.itcast.thread;

import java.util.concurrent.Callable;

public class CallableWorker implements Callable<String> {
    private final String name;

    public CallableWorker(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 执行任务：" + name);
        Thread.sleep(1000); // 模拟任务耗时
        return "任务 " + name + " 完成 by " + Thread.currentThread().getName();
    }
}