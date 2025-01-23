package com.example.demo.threadSafe;

import java.util.concurrent.CountDownLatch;

public class threadSafeDemo {
    public static void main(String[] args) throws InterruptedException {
        int size = 3;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    System.out.println(System.currentTimeMillis());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(100);
        countDownLatch.countDown();
    }
}
