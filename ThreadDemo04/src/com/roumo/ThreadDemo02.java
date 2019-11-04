/**
 * 文件名：ThreadDemo02.java
 * 描述：
 **/
package com.roumo;

import java.util.concurrent.CyclicBarrier;

class Writer extends Thread{
    CyclicBarrier cyclicBarrier;
    public Writer(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始写入数据...");
        try {
            Thread.sleep(30);
            System.out.println(Thread.currentThread().getName() + "写入数据成功...");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "所有数据写入完毕...");
        }catch (Exception e){

        }
    }
}

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/11/1
 * @date 2019/11/1 16:02
 */
public class ThreadDemo02 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++){
            new Writer(cyclicBarrier).start();
        }
    }
}
