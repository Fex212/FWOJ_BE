package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class userServiceImpl implements userService{

    @Autowired
    private userMapper userMapperObject;
    @Override
    public int loginCheck(String username, String passwd) {
        return userMapperObject.loginCheck(username,passwd);
    }

    @Override
    public String createToken(String username) {
        long t = new Date().getTime();
        userMapperObject.createToken(username,String.valueOf(t));
        return String.valueOf(t);
    }
}
