/**
 * 文件名：ThreadDemo03.java
 * 描述：静态同步代码块
 **/
package com.roumo;

class ThreadTrain3 implements Runnable{
    private Object ob = new Object();
    //总共有100张火车票
    //当一个变量被static修饰的话存放在永久区，当class文件被加载的时候就会被初始化
    private static int train1count = 100;
    public boolean flag = true;

    /**
     * 一个线程使用同步函数，另一个线程使用同步代码块this可以实现线程同步
     * 一个线程使用同步函数，另一个线程使用同步代码块（非this）不能实现线程同步
     **/
    @Override
    public void run() {
        //使用this锁
        if (flag){
            while(train1count > 0){
                //同步代码块 synchronized 包裹需要线程安全的问题
                synchronized (ThreadTrain3.class){ //只能有一个线程进行访问，必须拿到锁的时候才能访问
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
    public static synchronized void sale(){
        //同步代码块 synchronized 包裹需要线程安全的问题
        if(train1count > 0){
            System.out.println(Thread.currentThread().getName()+",出售第"+ ( 100 - train1count + 1 ));
            train1count--;
        }
    }

}


/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/23
 * @date 2019/10/23 17:26
 */
public class ThreadDemo03 {

    /**
      * 在方法上加上synchronized同步函数
      * 1.非静态同步函数：同步函数使用this锁
      * 2.静态（static）同步函数：使用的是当前字节码文件
      *
      **/
    public static void main(String[] args) throws InterruptedException {
        ThreadTrain3 threadTrain3 = new ThreadTrain3();
        Thread t1 = new Thread(threadTrain3,"窗口1");
        Thread t2 = new Thread(threadTrain3,"窗口2");
        t1.start();
        Thread.sleep(40);
        threadTrain3.flag = false;
        t2.start();
    }
    
}
