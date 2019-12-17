package com.thekingqj;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print() throws InterruptedException {
        int m;

        lock.lock();
        if (Thread.currentThread().getName().equals("A")) {
            m = 5;
        } else if (Thread.currentThread().getName().equals("B")) {
            m = 10;
        } else {
            m = 15;
        }

        if (flag == 1 && Thread.currentThread().getName().equals("B")) {
            c2.await();
        }
        if (flag == 1 && Thread.currentThread().getName().equals("C")) {
            c3.await();
        }
        if (flag == 2 && Thread.currentThread().getName().equals("A")) {
            c1.await();
        }
        if (flag == 2 && Thread.currentThread().getName().equals("C")) {
            c3.await();
        }
        if (flag == 3 && Thread.currentThread().getName().equals("A")) {
            c1.await();
        }
        if (flag == 3 && Thread.currentThread().getName().equals("B")) {
            c2.await();
        }
        try {
            System.out.println(Thread.currentThread().getName() + "\t\t\t" + m);
            for (int i = 0; i < m; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            if (Thread.currentThread().getName().equals("A")) {
                flag = 2;
                c2.signal();
            }
            if (Thread.currentThread().getName().equals("B")) {
                flag = 3;
                c3.signal();
            }
            if (Thread.currentThread().getName().equals("C")) {
                flag = 1;
                c1.signal();
            }


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

public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "C").start();
    }

}
