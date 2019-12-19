package com.thekingqj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 连接池思想
 *
 * 线程扩容的前提条件：核心线程已经用完了，并且阻塞队列也已经满了，这个时候在没有达到最大线程数时，线程池会自动的扩容到最大值
 *
 *
 * 七大参数：
 *  1.int corePoolSize,//核心线程数
 *    int maximumPoolSize,//最大线程数
 *    long keepAliveTime,//非核心线程且空闲线程的最大存活时间
 *    TimeUnit unit,//存活时间的类型
 *    BlockingQueue<Runnable> workQueue,//阻塞队列的类型
 *    ThreadFactory threadFactory,//创建线程池的工厂（默认就行）
 *    RejectedExecutionHandler handler//拒绝策略默认就行
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {


        /**
         * 注意线程池这三个都不用，他们各自有各自的缺点，线程池需要自己手写
         */
        ExecutorService threadPool= Executors.newFixedThreadPool(3);//一池指定线程数 注意：底层的阻塞队列是LinkedBlockingQueue，他的容量接近无限大
        ExecutorService threadPool1= Executors.newSingleThreadExecutor();//一池一线程   注意：底层的阻塞队列是LinkedBlockingQueue，他的容量接近无限大
        ExecutorService threadPool2= Executors.newScheduledThreadPool(3);//一池N个线程  注意，该线程池的最大线程数量接近无限大

        for (int i = 0; i < 10; i++) {
            final int temp=i;
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+temp);
            });
        }

        threadPool.shutdown();











    }
}
