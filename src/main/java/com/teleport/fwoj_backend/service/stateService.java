package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teleport.fwoj_backend.pojo.state;

import java.util.Date;
import java.util.List;

public interface stateService {

    //查询列表(页数,每页几条),返回List
    List<state> getStateList(Integer page, Integer pre);
    //查询总数
    Integer getStateSum();
    //查询详情
    state getStateDetail(Integer id);
    //增加一条state
    String addState(String code,int problemId,String token,String language) throws JsonProcessingException;
}
