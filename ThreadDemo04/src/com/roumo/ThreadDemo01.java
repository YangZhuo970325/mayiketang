/**
 * 文件名：ThreadDemo01.java
 * 描述：
 **/
package com.roumo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Vector和ArrayList区别
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/25
 * @date 2019/10/25 17:23
 */
public class ThreadDemo01 {
    /**
      * Vector和ArrayList的区别
      * 实现原理都是通过数组实现-查询速度快，增加、修改、删除速度慢
      * 区别：线程安全问题
      * vector是线程安全的（上锁的集合），ArrayList线程不安全
      * ArrayList效率高
      **/

    /**
      * HashTable和HashMap的区别
      * HashTable是线程安全的，HashMap是线程不安全的
      * 链表+数组 put HashCode取模得到下表位置 一致性取模算法
      *
      **/

    /**
      * jdk1.5之后，发明了一种新的并发包ConcurrentHashMap
      * 将一个整体拆分成多个小的hashTable,默认分成16段
      * 减少了锁的竞争
      **/
    public static void main(String[] args) {
        Vector<String> vector = new Vector<String>();
        vector.add("1");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("");
        Hashtable<Object,Object> hashtable = new Hashtable<>();
        hashtable.put("123","12e123");
        hashtable.get("123");
        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put("232",23);
        //ConcurrentHashMap
    }
}
