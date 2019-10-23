/**
 * 文件名：CreateThreadDemo06.java
 * 描述：
 **/
package com.roumo.threadDemo01;

import com.roumo.threadDemo01.entity.UserEntity;
import com.roumo.threadDemo01.util.ListUtils;
import java.util.ArrayList;
import java.util.List;

class UserSendThread implements Runnable{

    private List<UserEntity> listUser;
    public UserSendThread(List<UserEntity> listUser){
        this.listUser = listUser;
    }

    @Override
    public void run() {
        for (UserEntity userEntity : listUser){
            System.out.println(Thread.currentThread().getName()+","+userEntity.toString());
        }
        System.out.println();
    }
}

/**
 *
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/23
 * @date 2019/10/23 11:15
 */
public class CreateThreadDemo06 {

    public static void main(String[] args) {
        //1.初始化数据
        List<UserEntity> userEntityList = initUser();
        //2.定义每个线程分批发送大小
        int userCount = 2;
        //3.计算每个线程需要分批跑的数据
        List<List<UserEntity>> splitList = ListUtils.spiltList(userEntityList,userCount);
        for (int i = 0; i < splitList.size(); i++){
            List<UserEntity> list = splitList.get(i);
            UserSendThread userSendThread = new UserSendThread(list);
            //4.分批发送
            Thread thread = new Thread(userSendThread,"线程"+i);
            thread.start();
        }

    }

    static private List<UserEntity> initUser(){
        List<UserEntity> list = new ArrayList<UserEntity>();
        for(int i = 0; i < 19; i ++){
            list.add(new UserEntity("userId" + i, "userName:" + i));
        }
        return list;
    }
}
