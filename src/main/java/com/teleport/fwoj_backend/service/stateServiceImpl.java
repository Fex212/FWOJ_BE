package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.stateMapper;
import com.teleport.fwoj_backend.pojo.state;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class stateServiceImpl implements stateService {

    @Autowired
    private stateMapper stateMapperObject;
    @Override
    public List<state> getStateList(Integer page, Integer pre) {
        int start = pre * (page - 1);
        int num = pre;
        List<state> stateObject = stateMapperObject.getStateList(start,num);
//        System.out.println(stateObject);
        return stateObject;
    }

    @Override
    public Integer getStateSum() {
        return stateMapperObject.getStateSum();
    }

    @Override
    public state getStateDetail(Integer id) {
        state stateObject = stateMapperObject.getStateDetail(id);
//        System.out.println(stateObject);
        return stateObject;
    }

    @Override
    public boolean addState(int problemId, int authorId, Date date, String language, String code) {
        if(stateMapperObject.addState(problemId,authorId,date,language,code) == 1)
            return true;
        else
            return false;
    }
}
