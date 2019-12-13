package com.thekingqj;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//锁    操作    资源
class Tickect {
    private int m=30;
    private  Lock lock=new ReentrantLock();
    public void buy(){
        lock.lock();
                try {
                    if (m >0) {
                        System.out.println(Thread.currentThread().getName()+"第："+(m--)+"张票被卖出");
                    }
                } finally {
                 lock.unlock();
                }

    }
}


public class CurrentLock {
    public static void main(String[] args) {
        Tickect tickect = new Tickect();
        new Thread(()->{ for (int i = 0; i <30 ; i++) tickect.buy(); },"A").start();
        new Thread(()->{ for (int i = 0; i <30 ; i++) tickect.buy(); },"B").start();
        new Thread(()->{ for (int i = 0; i <30 ; i++) tickect.buy(); },"C").start();
    }
}
