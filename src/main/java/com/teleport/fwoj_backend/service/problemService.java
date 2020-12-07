package com.teleport.fwoj_backend.service;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface problemService {
    //查询列表(页数,每页几条),返回List
    String getProblemList(int page,int pre) throws JsonProcessingException;
    //查询问题详情
    String getProblemDetailById(int id) throws JsonProcessingException;

    //获取问题列表(Admin) id title createTime visible authorName
    String getProblemListAdmin(int page,int pre,String key,String token) throws JsonProcessingException;
    //查询问题详情(Admin)
    String getProblemDetailAdmin(String token,int id) throws JsonProcessingException;


    String changeProblemVisible(String token,int id) throws JsonProcessingException;

    //根据id删除问题
    String deleteProblemById(String token,int id) throws JsonProcessingException;

    //创建问题
    String createProblem(String token,String title,String des,String input,String output,String inputExample,String outputExample,String hint) throws JsonProcessingException;
    //根据id编辑问题id
    String editProblem(String token,String title,String des,String input,String output,String inputExample,String outputExample,
                    String hint,int id) throws JsonProcessingException;
}
