package com.thekingqj;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 三个线程之间的顺序调用。A->B->C
 */
class ShareResource1 {
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() throws InterruptedException {

        lock.lock();
                try {
                      while(flag!=1){
                          c1.await();
                      }
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName()+"\t"+i);
                    }
                    flag=2;
                    c2.signal();
                } finally {
                 lock.unlock();
                }


    }
    public void print10() throws InterruptedException {

        lock.lock();
                try {
                      while(flag!=2){
                          c2.await();
                      }
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName()+"\t"+i);
                    }
                    flag=3;
                    c3.signal();
                } finally {
                 lock.unlock();
                }


    }
    public void print15() throws InterruptedException {

        lock.lock();
                try {
                      while(flag!=3){
                          c3.await();
                      }
                    for (int i = 0; i < 15; i++) {
                        System.out.println(Thread.currentThread().getName()+"\t"+i);
                    }
                    flag=1;
                    c1.signal();
                } finally {
                 lock.unlock();
                }


    }
}


/**
 * @Description: 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 */

public class ThreadOrderAccess1 {
    public static void main(String[] args) {
        ShareResource1 resource = new ShareResource1();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "C").start();
    }

}
