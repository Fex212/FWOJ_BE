package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.problemMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class problemServiceImpl implements problemService{

    @Autowired
    private problemMapper problemMapperObject;
    @Autowired
    private userMapper userMapperObject;

    @Override
    public List<problem> getProblemList(Integer page, Integer pre) {
        int start = pre * (page - 1);
        int num = pre;
        return problemMapperObject.getProblemList(start,num);
    }

    @Override
    public Integer getProblemSum() {
        return problemMapperObject.getProblemSum();
    }

    //按id查找详情
    @Override
    public problem getProblemDetail(Integer id) {
        return problemMapperObject.getProblemDetail(id);
    }

    @Override
    public List<problem> getProblemListAdmin(int page, int pre, String token, String key) {
        int start = pre * (page - 1);
        int num = pre;
        return problemMapperObject.getProblemListAdmin(start,num,token,key);
    }

    @Override
    public boolean getProblemVisibleById(int id) {
        if(problemMapperObject.getProblemVisibleById(id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setProblemVisibleById(int id, boolean visible) {
        if(problemMapperObject.setProblemVisibleById(id,visible) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteProblemById(int id) {
        if(problemMapperObject.deleteProblemById(id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean createProblem(String title, String des, String input, String output, String inputExample, String outputExample, String hint,String authorName) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(new Date());
        if(problemMapperObject.createProblem(title,des,input,output,inputExample,outputExample,hint,
        0,0,dateTime,authorName,false) == 1)
            return true;
        else
           return false;
    }

    @Override
    public boolean editProblem(String title, String des, String input, String output, String inputExample, String outputExample, String hint, int id) {
        if(problemMapperObject.editProblem(title,des,input,output,inputExample,outputExample,hint,id) == 1)
            return true;
        else
            return false;
    }


}
