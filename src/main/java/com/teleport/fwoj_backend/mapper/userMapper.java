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
    //通过
}
