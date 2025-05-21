package cn.itcast.thread.sync;

import java.util.concurrent.Semaphore;


class SemaphoreDemo {
    private final Semaphore semaphore = new Semaphore(2); // 最多允许两个线程并发执行，访问临界资源
//    Semaphore(int permits)


    public void access() {
        try {
            semaphore.acquire();//请求一个许可（阻塞式）
            System.out.println(Thread.currentThread().getName() + " 获取许可，执行操作");
            Thread.sleep(100); // 模拟耗时操作
            System.out.println(Thread.currentThread().getName() + " 释放许可");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();//释放一个许可
        }
    }
}

public class SemaphoreTest {
    public static void main(String[] args) {
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 1; i <= 6; i++) {
            new Thread(demo::access, "线程" + i).start();
        }
    }
}