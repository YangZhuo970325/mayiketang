/**
 * 文件名：Demo003.java
 * 描述：
 **/
package com.roumo;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/28
 * @date 2019/10/28 17:46
 */
public class Demo003 {
    public static void main(String[] args) {
        List<Object> objectList =  new ArrayList<Object>();
        for(int i = 0; i < 20; i++){
            System.out.println("i:"+i);
            objectList.add(new byte[1*1024*1024]);
        }
        System.out.println("创建完毕");
    }
}
