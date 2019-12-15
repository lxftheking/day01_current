package com.thekingqj;

class Zi {
    private int m = 0;

    public synchronized void increment() throws InterruptedException {
        while (m == 0) {
            this.wait();
        }
        --m;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "\t" + m);
    }

    public synchronized void decrement() throws InterruptedException {
        while (m != 0) {
            this.wait();
        }
        ++m;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "\t" + m);
    }
}


/**
 * 1.   高内聚低耦合前提下，线程操作资源类
 * 2.   判断/干活/通知
 * 3.   防止多线程通信时，虚假唤醒的BUG(要是用wait则必须用while判断)
 */

public class ThreadWaitNotify {

    public static void main(String[] args) {
        Zi zi = new Zi();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    zi.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    zi.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    zi.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    zi.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
