/**
 * 文件名：ThreadDemo01.java
 * 描述：
 **/
package com.roumo;

class ThreadTrain1 implements Runnable{
    private Object ob = new Object();
    //总共有100张火车票
    private int train1count = 100;

    @Override
    public void run() {
        //为了能够模拟程序一直在抢票
        while (train1count > 0){
            try {
                Thread.sleep(50);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            //出售火车票
            sale();
        }
    }

    /**
      * 1.有一个线程已经拿到了锁，其他线程已经有了cpu执行的，一直排队，等待其他线程释放锁
      * 2.锁是什么时候被释放的？代码执行完毕或者是程序抛出异常都会被释放掉
      * 3.锁已经被释放掉，其他线程开始获取锁
      **/

    public void sale(){
        //同步代码块 synchronized 包裹需要线程安全的问题
        synchronized (ob){ //只能有一个线程进行访问，必须拿到锁的时候才能访问
            if(train1count > 0){
                System.out.println(Thread.currentThread().getName()+",出售第"+ ( 100 - train1count + 1 ));
                train1count--;
            }
        }
    }

}

/**
 * 线程安全
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/23
 * @date 2019/10/23 14:51
 */
public class ThreadDemo01 {

    /**
      * 什么是线程安全问题？
      * 当多个线程共享同一个全局变量做写操作的时候，可能会受到其他线程的干扰导致数据有问题，
      * 这种现象叫做线程安全问题，但是读取操作不会发生数据冲突问题
      **/

    /**
      * 线程之间如何同步（保证数据的原子性）
      * 解决办法
      * 1.synchroized 同步函数 --自动挡
      * 2.lock--jdk1.5并发包  --手动挡
      **/

    /**
      * 使用锁的条件
      * 1.必须要有两个线程以上，需要发生同步
      * 2.多个线程想要同步，必须用同一把锁
      * 3.保证只有一个线程进行执行
      *
      * 缺点：效率非常低
      **/

    public static void main(String[] args) {
        ThreadTrain1 threadTrain1 = new ThreadTrain1();
        Thread t1 = new Thread(threadTrain1,"窗口1");
        Thread t2 = new Thread(threadTrain1,"窗口2");
        t1.start();
        t2.start();
    }

}
