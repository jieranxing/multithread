package cn.itcast.thread;

public class SharedRunnable implements Runnable {
    private int count = 0;

    public void run() {
        while (true) {
            synchronized (this) {
                if (count >= 20) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " 访问 count = " + count);
                count++;
            }

            try {
                Thread.sleep(100);  // 模拟处理时间，便于线程交替运行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}