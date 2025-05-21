package cn.itcast.thread;

import java.util.Date;

public class CustomThread extends Thread {

    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("CustomThread: " + new Date().getTime());
        }
    }
}