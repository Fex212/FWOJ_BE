package com.teleport.fwoj_backend.service;

public interface userService {

    int loginCheck(String username,String passwd);
    String createToken(String username);
}
