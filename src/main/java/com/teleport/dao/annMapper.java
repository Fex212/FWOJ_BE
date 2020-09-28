package com.teleport.dao;

import com.teleport.pojo.ann;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface annMapper {

    //查询公告列表(页数,每页几条)
//    方法数为多个时，要加入@Param注解
    List<ann> getAnnList(@Param("start") int start,@Param("num") int num);
    int getAnnSum();
    ann getAnnDetail(int id);
}
