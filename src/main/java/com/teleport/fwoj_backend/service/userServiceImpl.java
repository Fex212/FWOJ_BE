package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class userServiceImpl implements userService{

    @Autowired
    private userMapper userMapperObject;
    @Override
    public int loginCheck(String username, String passwd) {
        return userMapperObject.loginCheck(username,passwd);
    }
    @Override
    public String createToken(String username)
    {
        byte[] lock = new byte[0];
        long w = 100000000;
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }
        String token = System.currentTimeMillis() + String.valueOf(r).substring(1);
        userMapperObject.createToken(username,token);
        return token;
    }

    @Override
    public String getUserName(String token) {
        return userMapperObject.getUserName(token);
    }

    @Override
    public int getUserId(String token) {
        return userMapperObject.getUserId(token);
    }

    @Override
    public String getUserType(String token) {
        return userMapperObject.getUserType(token);
    }

    @Override
    public boolean emailExist(String email) {
        if(userMapperObject.emailExist(email) == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean usernameExist(String username) {
        if(userMapperObject.usernameExist(username) == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean register(String email, String username, String passwd) {
        int r = userMapperObject.register(email,username,passwd);
        if(r == 0)
            return false;
        else
            return true;
    }


}
