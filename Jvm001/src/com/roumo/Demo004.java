/**
 * 文件名：Demo004.java
 * 描述：
 **/
package com.roumo;

/**
 * 栈溢出
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/28
 * @date 2019/10/28 17:59
 */
public class Demo004 {
    private static int count = 0;
    public static void getCount(){
        try {
            count++;
            getCount();
        }catch (Throwable t){
            System.out.println("最大的深度..."+count);
            t.printStackTrace();
        }
    }

    //栈溢出是方法中递归调用，不是循环调用方法
    public static void main(String[] args) {
        getCount();
    }
}
