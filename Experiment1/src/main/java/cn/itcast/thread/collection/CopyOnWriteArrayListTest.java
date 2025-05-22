package cn.itcast.thread.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // 写线程：不断添加元素
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add("元素" + i);
                System.out.println("添加：元素" + i);
                try {
                    Thread.sleep(50); // 模拟写入延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 读线程：不断读取元素
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("读取：" + list);
                try {
                    Thread.sleep(100); // 模拟读取延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writer.start();
        reader.start();
    }
}
