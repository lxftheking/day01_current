package com.thekingqj;


import java.util.concurrent.TimeUnit;

class Phone {

    //synchronized锁的是整个资源（当前对象/方法区中的类资源（Class））
    //（加锁时）同一时间段只能有一个线程进入，访问
    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("****sendEmail");
    }

    public static synchronized void sendSMS() {
        System.out.println("****sendSMS");
    }
    public void hello(){
        System.out.println("*****Hello");
    }
}


/**
 * 多线程的8锁
 * 1.   标准访问，问先打印邮件还是短信
 * 2.   email方法新增暂停3秒冲，问先打印邮件还是短信
 * 3.   新增普通方法hello，问先打印email还是hello
 * 4.   两部手机，问问先打印邮件还是短信
 * 5.   两个静态的同步方法，一部手机，问问先打印邮件还是短信
 * 6.   两个静态的同步方法，两部手机，问问先打印邮件还是短信
 * 7.   一个静态同步方法，一个普通同步方法，一部手机，问问先打印邮件还是短信
 * 8.   一个静态同步方法，一个普通同步方法，两部部手机，问问先打印邮件还是短信
 *
 * 笔记：
 * 1-2
 *一个对象 里面如果有多个synchronized方法，某一个时刻内， 只要一个线程去 调用其中的一个synchronized 方法了，
 * 其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去 访问这些synchronized方法
 * 锁的是当前对象this,被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法

   3-4
 *加个普通方法后发现和同步锁无关
 *换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 *   5-6
 * 都换成静态同步方法后，情况又变化
 * 若是普通同步方法，new
 * this,具体的一 部部手机, 所有的普通同步方法用的都是同一把锁实例对象本身，
 * 若是静态同步方法，static Class ，唯一的一个模板
 * synchronized.是实现同步的基础: Java 中的每一个对象 都可以作为锁。
 * 具体表现为以下3种形式。
 * 对于普通同步方法，锁是当前实例对象。它等同于对于同步方法块，锁是synchonized括 号里配置的对象。
 * 对于静态同步方法，锁是当前类的class对象本身,
 * 7-8
 * 当一个线程试图访问同步代码时它首先必须得到锁，退出或抛出异常时必须释放锁。
 * 所有的普通同步方法用的都是同一-把锁一 实例对象本身， ，就是new出来的具体实例对象本身
 * 也就是说如果一个实例对象的 普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放锁后才能获取锁,
 * 可是别的实例对象的普通同步方法因为跟该实例对象的普通同步方法用的是不同的锁，所以不用等待该实例对象已获取锁的普通
 * 同步方法释放锁就可以获取他们自己的锁。
 * 所有的静态同步方法用的也是同一-把锁-类对象本身， 就是我们说过的唯- -模板class
 * 具体实例对象this和唯一-模板class， 这两把锁是两个不同的对象，所以静态同步方法与普通同步方法之间是不会有竞态条件的。
 * 但是一旦一. 个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁,
 *
 */
public class Lock8 {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(() -> {

            try {
                Phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();



        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        new Thread(() -> {
            Phone.sendSMS();
            //phone.hello();
        }, "B").start();

    }
}
