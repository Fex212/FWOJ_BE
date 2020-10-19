package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userService {

    int loginCheck(String username,String passwd);
}
