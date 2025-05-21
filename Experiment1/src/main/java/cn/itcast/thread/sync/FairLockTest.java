package cn.itcast.thread.sync;

import java.util.concurrent.locks.ReentrantLock;

class FairLockDemo {
    //1. 设置了公平锁
    private final ReentrantLock lock = new ReentrantLock(true); // 公平锁
    /*
        true 参数使得 ReentrantLock 以 公平模式运行。
        内部使用一个 FIFO 等待队列（CLH队列）来保证公平性。
        */
    public void print() {
        //3. 每个线程在持锁后执行任务
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获得锁");
            Thread.sleep(100); // 模拟持有锁的时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
/*
        每个线程拿到锁之后睡 100ms，模拟一段临界区操作，这样可以明显观察到多个线程的执行顺序。
*/
    }
}

public class FairLockTest {
    public static void main(String[] args) {
        FairLockDemo demo = new FairLockDemo();
        //2. 模拟多个线程争抢锁
        for (int i = 1; i <= 5; i++) {
            new Thread(demo::print, "线程" + i).start();
        }
/*
        创建了 5 个线程，每个线程都调用 demo.print() 方法，去争夺这把锁。
*/
    }
}