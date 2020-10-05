package com.teleport.service;

import com.teleport.pojo.problem;
import java.util.List;

public interface problemService {
    //查询问题列表(页数,每页几条),返回List
    List<problem> getProblemList(int page,int pre);
    //查询问题总数
    int getProblemSum();
    problem getProblemDetail(int id);
}
