package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

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
}
