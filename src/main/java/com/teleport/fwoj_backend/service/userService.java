package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teleport.fwoj_backend.pojo.user;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface userService {

    //登录
    String login(String username,String passwd) throws JsonProcessingException;
    //注册
    String register(String email,String username,String passwd) throws JsonProcessingException;


    //根据用户名查找available是否为true
    boolean getAvailableByUsername(String username);
    //根据用户名设置available
    boolean setAvailableByUsername(String username,boolean available);


    //根据token获取用户名，id，用户类型,邮箱
    String getUserNameByToken(String token) throws JsonProcessingException;
    String getUserIdByToken(String token) throws JsonProcessingException;
    String getUserTypeByToken(String token) throws JsonProcessingException;
    String getUserEmail(String token);

    //根据token获取用户名，id，用户类型,邮箱,sign,site,github
    String getUserPersonInfo(String token) throws JsonProcessingException;
    //根据token更新sign,site,github
    String updateUserPersonInfo(String token,String sign,String site,String github)throws JsonProcessingException;
    //根据username获取type,sign,site,github
    String getUserCardInfo(String username) throws JsonProcessingException;


    //查询email是否存在
    boolean emailExist(String email);
    //查询username是否存在
    boolean usernameIsExist(String username);
    //  根据email查询除自己之外的用户数量
    int getEmailNumExpect(String email,int id);
    //  根据username查询除自己之外的用户数量
    int getUsernameNumExpect(String username,int id);

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
    //提供原密码修改密码
    String updatePasswordByPrePassword(String token,String oldpasswd,String passwd) throws JsonProcessingException;

    String uploadAvatar(MultipartFile file,String token) throws JsonProcessingException;
    byte[] getAvatarUrl(String username) throws IOException;
}
