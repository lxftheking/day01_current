package com.thekingqj;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class MyThread implements Callable<String> {


    @Override
    public String call() throws Exception {
        System.out.println("***********callable");
        return "123456";
    }
}


/**
 * Callable实现线程的另一种方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask=new FutureTask(new MyThread());
        new Thread(futureTask, "A").start();
        System.out.println(futureTask.get());
    }
}
