package com.thekingqj;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * 阻塞队列 三种常用的阻塞队列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
//        BlockingQueue<String> queue=new ArrayBlockingQueue<>(3);
        BlockingQueue<String> queue=new LinkedBlockingQueue<>();
        for (int i = 0; i < 4; i++) {
            System.out.println(queue.offer(i+"",3, TimeUnit.SECONDS));
        }
        for (int i = 0; i <4 ; i++) {
            System.out.println(queue.peek());
            System.out.println(queue.poll());
        }
       /* for (int i = 0; i < 3; i++) {
            System.out.println(queue.add(i + ""));
        }
        for (int i = 0; i <4 ; i++) {
            System.out.println(queue.element());
            System.out.println(queue.remove());
        }*/
    }
}
