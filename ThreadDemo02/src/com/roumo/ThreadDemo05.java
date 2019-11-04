/**
 * 文件名：ThreadDemo05.java
 * 描述：java内存模型、volatile
 **/
package com.roumo;


class ThreadVolatileDemo extends Thread{
    private volatile boolean flag = true;
    @Override
    public void run() {
        System.out.println("子线程开始执行...");
        while (flag){

        }
        System.out.println("子线程执行结束...");
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public boolean getFlag(){
        return this.flag;
    }
}
/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/23
 * @date 2019/10/23 22:16
 */
public class ThreadDemo05 {
    /**
      * 多线程的三个特性
      * 原子性 独一无二、一致性（保证线程安全问题）
      * 可见性 java内存模型
      * 有序性 join,wait,notif(多线程之间通讯)
      **/

    /**
      * java内存模型 属于多线程可见性 jmm
      * java内存结构 jvm内存分配
      *
      * java内存模型，决定一个线程与另一个线程是否可见
      * java内存模型  主内存（主要存放共享的全局变量） 、 私有本地内存（本地线程私有变量）
      **/

    /**
      * volatile作用：保证线程之间可见，但不保证原子性
      **/

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        Thread.sleep(3000);
        //主线程将flag改为false
        threadVolatileDemo.setFlag(false);
        System.out.println("flag值已经修改为false");
        Thread.sleep(1000);
        System.out.println(threadVolatileDemo.getFlag());
    }
}
