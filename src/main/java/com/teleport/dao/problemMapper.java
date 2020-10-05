package com.teleport.dao;

import com.teleport.pojo.problem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface problemMapper{

    //查询问题列表(页数,每页几条)
    List<problem> getProblemList(@Param("start") int start,@Param("num") int num);
    //查询问题总记录数
    int getProblemSum();
    problem getProblemDetail(int id);

}
