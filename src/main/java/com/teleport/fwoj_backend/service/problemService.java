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

    //获取问题列表(Admin) id title createTime visible authorName
    List<problem> getProblemListAdmin(int page,int pre,String token,String key);

    //根据id获取问题的visible
    boolean getProblemVisibleById(int id);
    //根据id设置问题的visible
    boolean setProblemVisibleById(int id,boolean visible);
    //根据id删除问题
    boolean deleteProblemById(int id);

    //创建问题
    boolean createProblem(String title,String des,String input,String output,String inputExample,String outputExample,String hint,String authorName);
    //根据id编辑问题id
    boolean editProblem(String title,String des,String input,String output,String inputExample,String outputExample,
                    String hint,int id);
}
