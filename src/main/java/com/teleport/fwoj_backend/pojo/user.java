package com.teleport.fwoj_backend.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    int id;
    String username;
    String passwd;
    String des;
    String avatar;
    String type;
    String email;
    boolean available;
    String token;
    //按username更改用户的available
    public user(String username,boolean available)
    {
        this.username = username;
        this.available = available;
    }
    public user(String username,String token)
    {
        this.username = username;
        this.token = token;
    }
    public user(String email,String username,String passwd)
    {
        this.email = email;
        this.username = username;
        this.passwd = passwd;
    }

    //admin页面列表对象
    public user(int id,String username,String email,String type,boolean available)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = type;
        this.available = available;
    }

    //admin编辑用户详情对象
    public user(int id,String username,String email,String type,String des)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = type;
        this.des = des;
    }

    //admin编辑用户提交对象
    public user(int id,String email,String username,String type,String des,String passwd)
    {
        this.id = id;
        this.email = email;
        this.username = username;
        this.type = type;
        this.des = des;
        this.passwd = passwd;
    }

}
