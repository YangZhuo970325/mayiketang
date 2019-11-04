/**
 * 文件名：ThreadDemo04.java
 * 描述：死锁
 **/
package com.roumo;

class ThreadTrain4 implements Runnable{
    private Object ob = new Object();
    private static int train1count = 100;
    public boolean flag = true;

    /**
     * 线程1拿到ob锁，等待this锁
     * 线程2拿到this锁，等待ob锁
     * 死锁的产生：同步中嵌套同步，互相不释放
     **/
    @Override
    public void run() {
        if (flag){
            while (true){
                synchronized (ob){
                    //出售火车票
                    sale();
                }
            }
        }else{
            while (true){
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
    public synchronized void sale(){
        synchronized (ob){
            //同步代码块 synchronized 包裹需要线程安全的问题
            if(train1count > 0){
                try{
                    Thread.sleep(50);
                }catch (Exception e){

                }
                System.out.println(Thread.currentThread().getName()+",出售第"+ ( 100 - train1count + 1 ));
                train1count--;
            }
        }

    }

}

/**
 * 死锁
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/23
 * @date 2019/10/23 21:46
 */
public class ThreadDemo04 {
    /**
      * 多线程死锁现象，同步中嵌套同步，无法释放。一直等待，变为死锁
      **/
    public static void main(String[] args) throws InterruptedException {
        ThreadTrain4 threadTrain4 = new ThreadTrain4();
        Thread t1 = new Thread(threadTrain4,"窗口1");
        Thread t2 = new Thread(threadTrain4,"窗口2");
        t1.start();
        Thread.sleep(40);
        threadTrain4.flag = false;
        t2.start();
    }
}
