package com.teleport.fwoj_backend.service;

public interface userService {

    int loginCheck(String username,String passwd);
    String createToken(String username);
    String getUserName(String token);
    int getUserId(String token);
    String getUserType(String token);
    //查询email是否存在
    boolean emailExist(String email);
    //查询username是否存在
    boolean usernameExist(String username);
    //注册
    boolean register(String email,String username,String passwd);
}
