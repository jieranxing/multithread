package cn.itcast.thread.collection;

import java.util.concurrent.*;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        //创建一个写入任务：每个线程向map种写入1000个键值对
        Runnable writer = () -> {
            for (int i = 0; i < 1000; i++) {
                //将当前线程写入的信息，方便观察执行过程
                map.put(Thread.currentThread().getName() + "-" + i, i);
                //输出当前线程写入的信息，方便观察执行过程
                System.out.println(Thread.currentThread().getName()+"put:"+i);
            }
        };

        //输出两个线程，模拟并发写入
        Thread t1 = new Thread(writer, "线程A");
        Thread t2 = new Thread(writer, "线程B");

        //启动线程
        t1.start();
        t2.start();

        try {
            //等待两个线程都执行完毕
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //输出最终map的大小，应为2000（1000*2），从而验证 ConcurrentHashMap 的线程安全性。
        System.out.println("最终 map 大小为: " + map.size());
    }
}
