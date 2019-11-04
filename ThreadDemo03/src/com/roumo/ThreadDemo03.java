/**
 * 文件名：ThreadDemo03.java
 * 描述：stopThread
 **/
package com.roumo;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

class StopThreadDemo extends Thread{
    private volatile boolean flag = true;
    @Override
    public synchronized void run() {
        System.out.println("子线程开始执行");
        while(flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopThread();
            }
        }
        System.out.println("子线程结束执行");
    }

    public void stopThread(){
        System.out.println("stopThread方法被调用");
        this.flag = false;
    }
}

/**
 * stopThread demo
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/25
 * @date 2019/10/25 14:36
 */
public class ThreadDemo03 {
    /**
      *
      *
      **/
    public static void main(String[] args) throws InterruptedException {
        StopThreadDemo threadDemo = new StopThreadDemo();
        threadDemo.start();
        for(int i = 0; i < 10; i++){
            System.out.println("我是主线程，i="+i);
            Thread.sleep(1000);
            if(i == 3){
                //threadDemo.stopThread();
                threadDemo.interrupt();
            }
        }
    }
}
