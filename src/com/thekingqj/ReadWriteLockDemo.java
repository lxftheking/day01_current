package com.thekingqj;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
   private volatile Map<String ,String> maps=new HashMap<>();
  // Lock lock=new ReentrantLock();
  ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();

   public void put(String key,String value){
       rwl.writeLock().lock();
               try {
                   System.out.println(Thread.currentThread().getName()+"\t写开始");
                   maps.put(key,value);
                   System.out.println(Thread.currentThread().getName()+"\t写结束");
               } finally {
                rwl.writeLock().unlock();
               }


   }
    public void get(String key){
       rwl.readLock().lock();
               try {
                   System.out.println(Thread.currentThread().getName()+"\t读开始");
                   String m=maps.get(key);
                   System.out.println(Thread.currentThread().getName()+"\t读结束\t"+m);
               } finally {
                   rwl.readLock().unlock();
               }


    }



}


/**
 *  读写锁（读操作共享，写操作唯一）
 *
 * 即：
 *          读-读  可以共存
 *          读-写  不可以共存
 *          写-写   不可以共存
 *
 *   演示：
 *       不加锁： 写error，读并发
 *       加锁：   写不并发，但是读影响性能（在一写一读的情况下，不是不能用）
 *       加读写锁：写不并发   读可以并发   （一写多读的情况下更适用）
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();

        for (int i = 1; i <=10 ; i++) {
            final int temp=i;
            new Thread(() -> {
                     myCache.put(temp+"",temp+"");
                    },String.valueOf(i) ).start();
        }
        TimeUnit.SECONDS.sleep(1);
        for (int i = 1; i <=10 ; i++) {
            final int temp=i;
            new Thread(() -> {
                     myCache.get(temp+"");
                    },String.valueOf(i) ).start();
        }
    }
}
