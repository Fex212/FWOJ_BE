package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.problem;

import java.util.List;

public interface problemService {
    //查询列表(页数,每页几条),返回List
    List<problem> getProblemList(Integer page,Integer pre);
    //查询总数
    Integer getProblemSum();
    //查询详情
    problem getProblemDetail(Integer id);
}
