package com.teleport.fwoj_backend.service;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface stateService {
    //查询列表(页数,每页几条),返回List
    String getStateList(int page, int pre) throws JsonProcessingException;
    //查询详情
    String getStateDetail(int id) throws JsonProcessingException;
    //增加一条state
    String addState(String code,int problemId,String token,String language) throws JsonProcessingException;
}
