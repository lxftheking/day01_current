package com.thekingqj;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 *   其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 *   当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 *   题目：6个学生+班长在教室上自习，只有班长有钥匙锁门，班长只有等六个学生都走完才能锁门
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            final int temp=i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+temp+"离开教室");
                countDownLatch.countDown();
                    }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长离开教室");
    }
}
