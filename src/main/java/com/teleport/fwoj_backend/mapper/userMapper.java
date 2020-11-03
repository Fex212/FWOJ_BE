package com.teleport.fwoj_backend.mapper;

import com.teleport.fwoj_backend.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface userMapper {
    //提供用户名密码查找是否有这个人
    int loginCheck(@Param("username") String username, @Param("passwd") String passwd);
    //token存到数据库并返回对象:
    int createToken(@Param("username") String username,@Param("token") String token);
    //通过token查找用户名
    String getUserName(@Param("token") String token);
    //通过token查找用户Id
    int getUserId(@Param("token") String token);
    //通过token查户type
    String getUserType(@Param("token") String token);
    //通过email查询用户数量
    int emailExist(String email);
    //通过username查询用户数量
    int usernameExist(String username);
//  根据email查询除自己之外的用户数量
    int getEmailNumExpect(String email,int id);
//  根据email查询除自己之外的用户数量
    int getUsernameNumExpect(String username,int id);
    //传入email username passwd写入
    int register(@Param("email") String email,@Param("username") String username,@Param("passwd") String passwd);
    //获取用户列表
    List<user> getUserList(@Param("start") int start, @Param("num") int num,@Param("key") String key);
    //获取用户总数
    int getUserNum();
    //查询token对应的type
    String getTypeByToken(String token);
    //根据id查询用户详细信息
    user getUserDetailById(int id);
    //编辑用户
    int editUserDetail(@Param("email") String email,@Param("username") String username,@Param("type") String type,@Param("des") String des,@Param("passwd") String passwd,@Param("id") int id);
    int editUserDetailWithoutPasswd(@Param("email") String email,@Param("username") String username,@Param("type") String type,@Param("des") String des,@Param("id") int id);
    //根据id删除用户
    int deleteUser(int id);
}
