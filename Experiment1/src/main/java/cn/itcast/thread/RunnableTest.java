package cn.itcast.thread;

public class RunnableTest {
    public static void main(String[] args) {
        SharedRunnable task = new SharedRunnable(); // 共享一个 Runnable 对象
        // 启动多个线程共享任务
        Thread t1 = new Thread(task, "线程A");
        Thread t2 = new Thread(task, "线程B");
        Thread t3 = new Thread(task, "线程C");
        Thread t4 = new Thread(task, "线程D");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}