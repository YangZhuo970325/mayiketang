/**
 * 文件名：ThreadDemo01.java
 * 描述：多线程通讯
 **/
package com.roumo;

class Res{
    public String userName;
    public String sex;
    // true 生产者线程等待，消费者线程进行消费   false 生产者线程进行生产，消费者线程等待
    public boolean flag = false;
}

/**
 * wait()的作用：让当前线程从运行状态变为休眠状态，释放锁的资源
 * notify()的作用：让当前线程从休眠状态变为运行状态
 * wait()和notify()只能在同步中使用，而且是一起使用
 * 只有锁资源才能使用wait(),notify(),并且只能是同一个锁资源
 **/

/**
  *
  * wait与sleep的区别（都是休眠）
  * wait用于同步中，可以释放锁资源
  * sleep不会释放锁资源的
  * wait需要notify才能从休眠状态变为运行状态
  * sleep是时间到期，从休眠状态变为运行状态
  *
  **/

//写线程
class Output extends Thread{

    Res res;

    public Output(Res res){
        this.res = res;
    }

    @Override
    public void run() {
        //写操作
        int count = 0;
        while(true){
            synchronized (res){
                if(res.flag){
                    try {
                        res.wait(); //让当前线程从运行状态变为休眠状态 并且释放锁的资源
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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
                res.notify();
            }
        }
    }
}

//读线程
class Input extends Thread{
    Res res;

    public Input(Res res){
        this.res = res;
    }

    @Override
    public void run() {
        while(true){
            synchronized (res){
                if(!res.flag){
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(res.userName + "," + res.sex);
                res.flag = false;
                res.notify();
            }
        }
    }
}

/**
 * 多线程之间通讯
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/24
 * @date 2019/10/24 14:36
 */
public class ThreadDemo01 {

    /**
      * 什么是多线程之间通讯？
      * 多个线程对同一个资源（共享资源），每个线程对共享资源做的动作不同，操作不同
      * 多线程通讯的 生产者线程与消费者线程都要进行同步
      **/


    public static void main(String[] args) {
        Res res = new Res();
        Output output = new Output(res);
        Input input = new Input(res);
        output.start();
        input.start();
    }
}
