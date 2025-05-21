package cn.itcast.thread.sync;

import java.util.concurrent.Semaphore;

public class FairVsNonFairSemaphore {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== 非公平信号量测试 =====");
        testSemaphore(new Semaphore(2, false)); // 非公平
//      Semaphore(int permits, boolean fair)
        Thread.sleep(3000); // 分隔两次测试

        System.out.println("\n===== 公平信号量测试 =====");
        testSemaphore(new Semaphore(2, true)); // 公平
    }


    public static void testSemaphore(Semaphore semaphore) {
        for (int i = 1; i <= 5; i++) {
            int threadNum = i;
            new Thread(() -> {
                String threadName = "线程" + threadNum;
                System.out.println(threadName + " 准备获取许可");
                try {
                    semaphore.acquire();
                    System.out.println(threadName + " 成功获取许可，执行中...");
                    Thread.sleep(1000); // 模拟耗时操作
                    System.out.println(threadName + " 执行完成，释放许可");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}