/**
 * 文件名：ThreadDemo04.java
 * 描述：
 **/
package com.roumo;

class ResNumber{
    public int count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 0;
        }
    };

    public String getNumber(){
        count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count + "";
    }
}

class LocalThreadDemo extends Thread{

    private ResNumber resNumber;

    public LocalThreadDemo(ResNumber resNumber){
        this.resNumber = resNumber;
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++){
            System.out.println(getName() + "," + resNumber.getNumber());
        }
    }
}

/**
 * ThreadLock
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/25
 * @date 2019/10/25 15:56
 */
public class ThreadDemo04 {
    /**
      * 什么是ThreadLock？为每个线程提供一个局部变量
      **/
    public static void main(String[] args) {
        ResNumber resNumber = new ResNumber();
        LocalThreadDemo t1 = new LocalThreadDemo(resNumber);
        LocalThreadDemo t2 = new LocalThreadDemo(resNumber);
        LocalThreadDemo t3 = new LocalThreadDemo(resNumber);

        t1.start();
        t2.start();
        t3.start();
    }
}
