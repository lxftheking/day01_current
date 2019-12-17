package com.thekingqj;


import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tickect1 {
    private int m = 300;
    private Lock lock = new ReentrantLock();

    public void buy() {
        lock.lock();
        try {
            if (m > 0) {
                System.out.println(Thread.currentThread().getName() + "第：" + (m--) + "张票被卖出啦");
                System.out.println("HH33365585");
                System.out.println("HH33365585");
                System.out.println("哇哈哈哈哈哈，出错吧兄弟，来吧，我等您111");
            }
        } finally {
            lock.unlock();
        }

    }
}

public class Tickcet {

    public static void main(String[] args) {
        ExecutorService threadpool = Executors.newFixedThreadPool(3);
        Tickect1 t1 = new Tickect1();
        try {
            for (int i = 1; i <= 300; i++) {

                threadpool.execute(() -> {
                    t1.buy();
                });
            }
        } finally {
            threadpool.shutdown();

        }
    }
}
