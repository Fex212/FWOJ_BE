package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class userServiceImpl implements userService{

    @Autowired
    private userMapper userMapperObject;
    @Override
    public int loginCheck(String username, String passwd) {
        return userMapperObject.loginCheck(username,passwd);
    }

    @Override
    public boolean getAvailableByUsername(String username) {
        if(userMapperObject.getAvailableByUsername(username) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setAvailableByUsername(String username, boolean available) {
        if(userMapperObject.setAvailableByUsername(username,available) == 1)
            return true;
        else
            return false;
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
    public String getUserEmail(String token) {
        return userMapperObject.getUserEmail(token);
    }

    @Override
    public boolean emailExist(String email) {
        if(userMapperObject.emailExist(email) == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean usernameIsExist(String username) {
        if(userMapperObject.usernameExist(username) == 0)
            return false;
        else
            return true;
    }

    @Override
    public int getEmailNumExpect(String email, int id) {
        return userMapperObject.getEmailNumExpect(email,id);
    }

    @Override
    public int getUsernameNumExpect(String username, int id) {
        return userMapperObject.getUsernameNumExpect(username,id);
    }

    @Override
    public boolean register(String email, String username, String passwd) {
        int r = userMapperObject.register(email,username,passwd);
        if(r == 0)
            return false;
        else
            return true;
    }

    @Override
    public List<user> getUserList(Integer page, Integer pre,String key) {

        int start = pre * (page - 1);
        int num = pre;
        return userMapperObject.getUserList(start,num,key);
    }

    @Override
    public int getUserNum() {
        return userMapperObject.getUserNum();
    }

    @Override
    public boolean tokenIsAdmin(String token) {
        if(userMapperObject.getTypeByToken(token) != null && userMapperObject.getTypeByToken(token).equals("admin"))
            return true;
        else
            return false;
    }

    @Override
    public user getUserDetailById(int id) {
        return userMapperObject.getUserDetailById(id);
    }

    @Override
    public boolean editUserDetail(String email, String username, String type, String des, String passwd,int id) {
        if(userMapperObject.editUserDetail(email,username,type,des,passwd,id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean editUserDetailWithoutPasswd(String email, String username, String type, String des,int id) {
        if(userMapperObject.editUserDetailWithoutPasswd(email,username,type,des,id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteUser(int id) {
        if(userMapperObject.deleteUser(id) == 1)
            return true;
        else
            return false;
    }


}
