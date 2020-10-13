package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.problemMapper;
import com.teleport.fwoj_backend.pojo.problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class problemServiceImpl implements problemService{

    @Autowired
    private problemMapper problemMapperObject;

    @Override
    public List<problem> getProblemList(Integer page, Integer pre) {
        int start = pre * (page - 1);
        int num = pre;
        List<problem> list = problemMapperObject.getProblemList(start,num);
        return list;
    }

    @Override
    public Integer getProblemSum() {
        return problemMapperObject.getProblemSum();
    }

    //按id查找公告详情
    @Override
    public problem getProblemDetail(Integer id) {
        return problemMapperObject.getProblemDetail(id);
    }
}
