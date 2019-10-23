/**
 * 文件名：UserEntity.java
 * 描述：用户实体类
 **/
package com.roumo.threadDemo01.entity;

/**
 * 用户实体类
 *
 * @author yangzhuo-hd@139.com
 * @version 1.0，2019/10/23
 * @date 2019/10/23 11:45
 */
public class UserEntity {
    private String userId;
    private String userName;

    public UserEntity(String userId, String userName){
        super();
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
