package com.teleport.service;

import com.teleport.dao.problemMapper;
import com.teleport.pojo.problem;

import java.util.List;

public class problemServiceImpl implements problemService
{
    //调用dao层，设置annObject为set接口，方便spring管理
    private problemMapper problemMapperObject;
    public void setProblemMapperObject(problemMapper problemMapperObject) {
        this.problemMapperObject = problemMapperObject;
    }

    @Override
    public List<problem> getProblemList(int page, int pre) {

        int start = pre * (page - 1);
        int num = pre;
        List<problem> list = problemMapperObject.getProblemList(start,num);
        return list;
    }

    @Override
    public int getProblemSum() {
        return problemMapperObject.getProblemSum();
    }

    //按id查找公告详情
    @Override
    public problem getProblemDetail(int id) {
        return problemMapperObject.getProblemDetail(id);
    }

}
