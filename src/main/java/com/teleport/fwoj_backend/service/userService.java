package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface userService {

    //登录
    String login(String username,String passwd) throws JsonProcessingException;
    //注册
    String register(String email,String username,String passwd) throws JsonProcessingException;

    //根据token获取用户名，id，用户类型,邮箱
    String getUserNameByToken(String token) throws JsonProcessingException;
    String getUserIdByToken(String token) throws JsonProcessingException;
    String getUserTypeByToken(String token) throws JsonProcessingException;

    //根据token获取用户名，id，用户类型,邮箱,sign,site,github
    String getUserPersonInfo(String token) throws JsonProcessingException;
    //根据token更新sign,site,github
    String updateUserPersonInfo(String token,String sign,String site,String github)throws JsonProcessingException;
    //根据username获取type,sign,site,github
    String getUserCardInfo(String username) throws JsonProcessingException;

    //获取用户列表(页数,每页几条)
    String getUserList(Integer page, Integer pre, String key, String token) throws JsonProcessingException;

    //查询token是否是管理员
    String tokenIsAdmin(String token) throws JsonProcessingException;

    //根据id获取用户详细信息
    String getUserDetailById(String token,int id) throws JsonProcessingException;
    //根据id删除用户
    String deleteUser(String token,int id) throws JsonProcessingException;

    //更新用户信息(管理员)
    String editUserDetail(String token,String email,String username,String type,String des,String passwd,int id) throws JsonProcessingException;
    String editUserDetailWithoutPasswd(String token,String email,String username,String type,String des,int id) throws JsonProcessingException;

    //更改用户的available
    String changeUserAvailable(String token,String username) throws JsonProcessingException;

    //提供原密码修改密码
    String updatePasswordByPrePassword(String token,String oldpasswd,String passwd) throws JsonProcessingException;

    //更新用户头像
    String uploadAvatar(MultipartFile file,String token) throws JsonProcessingException;
    //获取用户头像
    byte[] getAvatarUrl(String username) throws IOException;

    //获取系统信息面板
    String getSystemInfo(String token) throws JsonProcessingException;

}
