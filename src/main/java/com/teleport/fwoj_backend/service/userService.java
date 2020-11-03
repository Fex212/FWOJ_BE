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
    //  根据email查询除自己之外的用户数量
    int getEmailNumExpect(String email,int id);
    //  根据username查询除自己之外的用户数量
    int getUsernameNumExpect(String username,int id);
    //注册
    boolean register(String email,String username,String passwd);
    //获取用户列表(页数,每页几条)
    List<user> getUserList(Integer page, Integer pre,String key);
    //获取用户总数
    int getUserNum();
    //查询token是否是管理员
    boolean tokenIsAdmin(String token);
    user getUserDetailById(int id);
    //更新用户信息(带密码和不带密码)
    boolean editUserDetail(String email,String username,String type,String des,String passwd,int id);
    boolean editUserDetailWithoutPasswd(String email,String username,String type,String des,int id);
    //根据id删除用户
    boolean deleteUser(int id);
}
