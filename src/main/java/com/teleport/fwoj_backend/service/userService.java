package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.user;

import java.util.List;

public interface userService {

    int loginCheck(String username,String passwd);
    String createToken(String username);
    String getUserName(String token);
    int getUserId(String token);
    String getUserType(String token);
    //查询email是否存在
    boolean emailExist(String email);
    //查询username是否存在
    boolean usernameIsExist(String username);
    //注册
    boolean register(String email,String username,String passwd);
    //获取用户列表(页数,每页几条)
    List<user> getUserList(Integer page, Integer pre);
    //获取用户总数
    int getUserNum();
}
