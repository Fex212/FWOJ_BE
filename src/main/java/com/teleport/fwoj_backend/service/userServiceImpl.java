package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class userServiceImpl implements userService{

    @Autowired
    private userMapper userMapperObject;
    @Override
    public boolean loginCheck(String username, String passwd)
    {
        if(userMapperObject.loginCheck(username,passwd) == 1)
            return true;
        else
            return false;
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
    public String getUserPersonInfo(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        int id = userMapperObject.getUserId(token);
        String username = userMapperObject.getUserName(token);
        String email = userMapperObject.getUserEmail(token);
        String type = userMapperObject.getUserType(token);
        String site = userMapperObject.getUserSiteByUsername(username);
        String github = userMapperObject.getUserGithubByUsername(username);
        String sign = userMapperObject.getUserSignByUsername(username);
        if(id != 0 && username != null && email != null && type != null)
        {
            s.put("error","0");
            s.put("id",id);
            s.put("username",username);
            s.put("email",email);
            s.put("type",type);
            s.put("github",github);
            s.put("sign",sign);
            s.put("site",site);
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String updateUserPersonInfo(String token, String sign, String site, String github) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.updateUserSiteByToken(token,site) == 1 &&
            userMapperObject.updateUserGithubByToken(token,github) == 1 &&
            userMapperObject.updateUserSignByToken(token,sign) == 1)
            s.put("error","0");
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getUserCardInfo(String username) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        //type,sign,site,github
        String type = userMapperObject.getUserTypeByUsername(username);
        String sign = userMapperObject.getUserSignByUsername(username);
        String site = userMapperObject.getUserSiteByUsername(username);
        String github = userMapperObject.getUserGithubByUsername(username);
        if(type != null)
        {
            s.put("error","0");
            s.put("type",type);
            s.put("site",site);
            s.put("sign",sign);
            s.put("github",github);
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
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

    @Override
    public String updatePasswordByPrePassword(String token, String oldpasswd, String passwd) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        String username = userMapperObject.getUserName(token);
        //原密码不对 error 1
        //原密码对
        if(userMapperObject.loginCheck(username,oldpasswd) == 1)
        {
            //更改密码
            if(userMapperObject.updatePassword(username,passwd) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }


}
