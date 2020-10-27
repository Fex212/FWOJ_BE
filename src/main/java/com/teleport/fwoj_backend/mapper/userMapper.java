package com.teleport.fwoj_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
    //传入email username passwd写入
    int register(@Param("email") String email,@Param("username") String username,@Param("passwd") String passwd);
}
