/**
 * 文件名：ThreadDemo02.java
 * 描述：
 **/
package com.roumo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Res01{
    public String userName;
    public String sex;
    public boolean flag;
    Lock lock = new ReentrantLock();
}

class Output01 extends Thread{

    Res01 res;
    Condition condition;
    public Output01(Res01 res, Condition condition){
        this.res = res;
        this.condition = condition;
    }

    @Override
    public void run() {
        int count  = 0;
        while (true){
            try {
                //开始上锁
                res.lock.lock();
                if(res.flag){
                    try {
                        condition.await();
                    }catch (Exception e){

                    }
                }
                if( count == 0){
                    res.userName = "小红";
                    res.sex = "女";
                }else{
                    res.userName = "小绿";
                    res.sex = "男";
                }
                count = (count + 1) % 2;
                res.flag = true;
                condition.signal();
            }catch (Exception e){

            }finally {
                //释放锁
                res.lock.unlock();
            }
        }
    }
}

class Input01 extends Thread{

    Res01 res;
    Condition condition;

    public Input01(Res01 res, Condition condition){
        this.res = res;
        this.condition = condition;
    }

    @Override
    public void run() {
        while(true){
            try {
                res.lock.lock();
                if(!res.flag){
                    try{
                        condition.await();
                    }catch (Exception e){

                    }
                }
                System.out.println(res.userName + "," + res.sex);
                res.flag = false;
                condition.signal();
            }catch (Exception e){

            }finally {
                res.lock.unlock();
            }

        }
    }
}

/**
 * jdk lock锁
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/25
 * @date 2019/10/25 9:46
 */
public class ThreadDemo02 {
    /**
      * synchronized(res){
      *
      * }
      * 从什么时候开始上锁      代码开始
      * 从什么时候开始释放锁    代码结束
      * 内置锁 自动化
      * 缺点：效率低、扩展不高、不能自定义
      **/


    /**
      * 并发包、Lock锁保证线程安全问题
      *
      **/

    /**
      * lock锁可以尝试非阻塞地获取锁，当前线程尝试获取锁，如果这一时刻没有被其他线程获取到，则成功获取并持有锁
      * lock接口能被中断地获取锁，与synchronized不同，获取到锁的线程能够响应中断，
      * 当获取到的锁的线程被中断时，中断异常将会被抛出，同时锁会被释放
      * lock接口在指定的截至时间之前获取锁，如果截至时间到了依旧无法获取锁，则返回。
      **/

    public static void main(String[] args) {
        Res01 res = new Res01();
        Condition condition = res.lock.newCondition();
        Output01 output = new Output01(res, condition);
        Input01 input = new Input01(res, condition);
        output.start();
        input.start();
    }
}
