/**
 * 文件名：ListUtils.java
 * 描述：分页工具类
 **/
package com.roumo.threadDemo01.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/23
 * @date 2019/10/23 11:54
 */
public class ListUtils {
    /**
      * @desc 对list进行切割
      * @param list 切割集合
      * @param pageSize 分页长度
      * @date 2019/10/23 12:46
      * @return List<List<T>> 返回分页数据
      **/
    static public <T> List<List<T>> spiltList(List<T> list, int pageSize){
        int listSize = list.size();
        int page = (listSize + (pageSize - 1)) / pageSize;
        List<List<T>> listArray = new ArrayList<List<T>>();
        for (int i = 0; i < page; i++){
            List<T> subList = new ArrayList<T>();
            for (int j = 0; j < listSize; j++){
                if(j < pageSize && (i * pageSize + j) < listSize){
                    subList.add(list.get((i * pageSize + j)));
                }else{
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;
    }
}
