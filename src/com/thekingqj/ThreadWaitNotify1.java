package com.thekingqj;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 class Zi1{
    private int m=0;
   private Lock lock= new  ReentrantLock();
   final Condition  condition=lock.newCondition();

    public  void increment() throws InterruptedException {
        lock.lock();
                try {
                    while(m==0){
                        condition.await();
                    }
                    --m;
                    condition.signalAll();
                    System.out.println(Thread.currentThread().getName()+"\t"+m);

                } finally {
                 lock.unlock();
                }
      }
    public  void decrement() throws InterruptedException {
        lock.lock();
                try {
                    while(m!=0){
                        condition.await();
                    }
                    ++m;
                    condition.signalAll();                    System.out.println(Thread.currentThread().getName()+"\t"+m);

                } finally {
                 lock.unlock();
                }

       }
}


public class ThreadWaitNotify1 {

    public static void main(String[] args) {
        Zi1 zi = new Zi1();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    zi.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    zi.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    zi.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    zi.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
