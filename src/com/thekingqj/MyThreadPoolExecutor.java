package com.thekingqj;


import java.util.concurrent.*;


/**
 * 1、在创建了线程池后，开始等待请求。
 * 2、当调用execute()方法添加一个请求l0-0任务时，线程池会做出如下判断：
 *   2.1如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务；
 *   2.2如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列；
 *   2.3如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务；
 *   2.4如果队列满了且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行。
 * 3、当一个线程完成任务时，它会从队列中取下一个任务来执行。
 * 4、当一个线程无事可做超过一定的时间（keepAliveTime）时，线程会判断：
 *     如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉。
 *     所以线程池的所有任务完成后，它最终会收缩到corePoolSize的大小。
 */
public class MyThreadPoolExecutor {

    public static void main(String[] args) {

        ExecutorService threadPool=new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 9; i++) {
            final int temp=i;
           threadPool.execute(()->{
               System.out.println(Thread.currentThread().getName()+"\t"+temp);
           });
        }
        threadPool.shutdown();

    }
}
