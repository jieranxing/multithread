package cn.itcast.thread;

import java.util.Date;

public class ThreadTest {
    public static void main(String[] args) {
        CustomThread customThread = new CustomThread();
        customThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("mainThread: " + new Date().getTime());
        }
    }
}