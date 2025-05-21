package cn.itcast.thread.sync;

import java.util.concurrent.CountDownLatch;

class CountDownDemo {
    private final CountDownLatch latch = new CountDownLatch(3);

    public void worker(String name) {
        System.out.println(name + " 正在执行任务...");
        try {
            Thread.sleep(100); // 模拟任务耗时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " 完成任务！");
        latch.countDown(); // 减少计数
    }

    public void await() {
        try {
            System.out.println("主线程等待所有任务完成...");
            latch.await(); // 等待计数为0
            System.out.println("所有任务完成，主线程继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CountDownTest {
    public static void main(String[] args) {
        CountDownDemo demo = new CountDownDemo();

        new Thread(() -> demo.worker("子线程1")).start();
        new Thread(() -> demo.worker("子线程2")).start();
        new Thread(() -> demo.worker("子线程3")).start();

        demo.await(); // 主线程等待
    }
}