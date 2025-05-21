package cn.itcast.thread.sync;

import java.util.concurrent.locks.ReentrantLock;


class ReentrantLockDemo {
    private ReentrantLock lock = new ReentrantLock();
    /*什么是重入锁？
    重入锁允许同一个线程多次获得同一把锁，而不会发生死锁。*/
    public void print() {
        //1. 线程再次获取锁（锁的线程身份识别 + 重入支持）
        //2. 锁的最终释放（计数器归零才能真正释放）

        lock.lock();//获取锁
        try {
            System.out.println(Thread.currentThread().getName() + " 获得锁，执行 print()");
            inner(); // 调用内部方法，测试重入
        } finally {
            lock.unlock();//释放锁
        }
    }
    /*
Thread-1 进入 print()，成功 lock.lock()；

        它接着调用 inner()，再次执行 lock.lock()；

        如果 ReentrantLock 不是可重入的，那么同一个线程第二次加锁会被阻塞，造成死锁；

        由于 ReentrantLock 是可重入的，所以线程可以多次加锁，并在 finally 中对应解锁。

*/

    private void inner() {
        lock.lock();//再次获取锁
        try {
            System.out.println(Thread.currentThread().getName() + " 再次获得锁，执行 inner()");
        } finally {
            lock.unlock();//再次释放
        }
    }
    //也就是说，线程加锁了两次，就需要 解锁两次。只有当所有的 unlock() 调用都完成后，内部的 holdCount 计数器才为 0，锁才被真正释放，其他线程才能进入。
/*    print() 和 inner() 都用了同一个锁对象 lock，
    且 inner() 在 print() 内部被调用，
    也就是说，同一个线程在尚未释放第一次锁的情况下再次加锁，
    这正是“可重入锁”的体现”*/
}

public class ReentrantLockTest {
    public static void main(String[] args) {
        final ReentrantLockDemo demo = new ReentrantLockDemo(); // 加上 final

        Runnable task = new Runnable() {
            @Override
            public void run() {
                demo.print();
            }
        };

        new Thread(task, "线程1").start();
        new Thread(task, "线程2").start();
        new Thread(task, "线程3").start();
        new Thread(task, "线程4").start();

    }
}