/**
 * 文件名：ThreadDemo02.java
 * 描述：证明同步函数就是this锁
 **/
package com.roumo;

class ThreadTrain2 implements Runnable{
    private Object ob = new Object();
    //总共有100张火车票
    private int train1count = 100;
    public boolean flag = true;

    /**
      * 一个线程使用同步函数，另一个线程使用同步代码块this可以实现线程同步
      * 一个线程使用同步函数，另一个线程使用同步代码块（非this）不
      **/
    @Override
    public void run() {
        //使用this锁
        if (flag){
            while(train1count > 0){
                //同步代码块 synchronized 包裹需要线程安全的问题
                synchronized (this){ //只能有一个线程进行访问，必须拿到锁的时候才能访问
                    if(train1count > 0){
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+",出售第"+ ( 100 - train1count + 1 ));
                        train1count--;
                    }
                }
            }
        }else{
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
    }

    /**
      * 同步函数使用的是什么锁？  this锁
      **/
    synchronized public void sale(){
        //同步代码块 synchronized 包裹需要线程安全的问题
            if(train1count > 0){
                System.out.println(Thread.currentThread().getName()+",出售第"+ ( 100 - train1count + 1 ));
                train1count--;
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
public class ThreadDemo02 {

    public static void main(String[] args) throws InterruptedException {
        ThreadTrain2 threadTrain2 = new ThreadTrain2();
        Thread t1 = new Thread(threadTrain2,"窗口1");
        Thread t2 = new Thread(threadTrain2,"窗口2");
        t1.start();
        Thread.sleep(40);
        threadTrain2.flag = false;
        t2.start();
    }

}
