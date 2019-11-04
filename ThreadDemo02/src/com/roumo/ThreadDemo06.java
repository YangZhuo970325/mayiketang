/**
 * 文件名：ThreadDemo06.java
 * 描述：原子类 jdk1.5并发包
 * 原子类是解决计数问题   同步函数是为了解决线程同步问题
 **/
package com.roumo;

import java.util.concurrent.atomic.AtomicInteger;

class VolatileNoAtomic extends Thread{
    //需要10个线程同时共享count static修饰关键字，存放在静态区，只会存放一次，所有线程都共享
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++){
            //count++;
            count.incrementAndGet();
        }
        System.out.println(getName()+","+count.get());
    }
}

/**
 * volatile不保证原子性
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/24
 * @date 2019/10/24 9:54
 */
public class ThreadDemo06 extends Thread{

    public static void main(String[] args) {
        //创建10个线程
        VolatileNoAtomic[] volatileNoAtomics = new VolatileNoAtomic[10];
        for (int i = 0; i < volatileNoAtomics.length; i++){
            volatileNoAtomics[i] = new VolatileNoAtomic();
        }
        for (VolatileNoAtomic volatileNoAtomic : volatileNoAtomics){
            volatileNoAtomic.start();
        }
    }
}
