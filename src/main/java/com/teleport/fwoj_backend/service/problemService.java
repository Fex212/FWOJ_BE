package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.problem;

import java.util.List;

public interface problemService {
    //查询问题列表(页数,每页几条),返回List
    List<problem> getProblemList(Integer page,Integer pre);
    //查询问题总数
    Integer getProblemSum();
    //查询问题详情
    problem getProblemDetail(Integer id);
}
