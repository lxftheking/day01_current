package com.thekingqj;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *1.    故障现象
 *  java.util.ConcurrentModificationException
 * 2.   导致原因
 *          ArrayList线程不安全的，在add的时候并没有加锁，导致多线程add没有执行完时进行了读，导致修改次数不一致，报并发修改异常
 *      即modCount != expectedModCount=false出现的问题集合的修改次数和迭代器的修改次数不一致导致的问题，
 * 3.   解决方案
        //Collections.synchronizedList(new ArrayList<>());
 *      //new Vector();
 *      //new CopyOnWriteArrayList();（写时复制，读写分离）
 *
 * 4.   优化建议
 *
 *
 * 写时复制
 * CopyOnWrite容器即写时复制的容器。往一个 容器添如元素的时候，不直接往当前容器object[ ]添加，而是先将当前容器object[]进行opy,
 * 复制出一个新的容 器object[] newElements，然后新的容器object[] newELements 里添加元素，添加完元素之后，
 * 再将原容器的引用指向新的容器setArray(newElements);。 这样做的好处是可以对CopyonWrite容器进行并发的读，
 * 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnwrite容器也是一种读 写分离的思想，读和写不同的容器
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        //System.out.println(UUID.randomUUID().toString().substring(0, 6));
        List list1=new ArrayList();
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        for (Object o : list1) {
            System.out.println(o.toString());
        }
        List list =new CopyOnWriteArrayList(); //Collections.synchronizedList(new ArrayList<>());//new Vector();//new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
