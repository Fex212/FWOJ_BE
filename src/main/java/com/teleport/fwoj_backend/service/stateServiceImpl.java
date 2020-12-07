package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.problemMapper;
import com.teleport.fwoj_backend.mapper.stateMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.state;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
public class stateServiceImpl implements stateService {

    @Autowired
    private stateMapper stateMapperObject;
    @Autowired
    private userMapper userMapperObject;
    @Autowired
    private problemMapper problemMapperObject;
    @Override
    public List<state> getStateList(Integer page, Integer pre) {
        int start = pre * (page - 1);
        int num = pre;
        List<state> stateObject = stateMapperObject.getStateList(start,num);
        return stateObject;
    }

    @Override
    public Integer getStateSum() {
        return stateMapperObject.getStateSum();
    }

    @Override
    public state getStateDetail(Integer id) {
        state stateObject = stateMapperObject.getStateDetail(id);
        return stateObject;
    }

    @Override
    public String addState(String code, int problemId, String token, String language) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        //token不合法
        if (userMapperObject.getUserNameByToken(token) == null
            || userMapperObject.getUserNameByToken(token).equals(""))
        {
            s.put("error", "1");
        }
        else {
            //提交到state表
            Date date = new Date();
            int authorId = userMapperObject.getUserIdByToken(token);
            int isVisible = problemMapperObject.getProblemVisibleById(problemId);
            if (isVisible == 1
                    &&
                    stateMapperObject.addState(problemId, authorId, date, language, code) == 1)
                s.put("error", "0");
            else
                s.put("error", "2");
        }
        return mapper.writeValueAsString(s);
    }


}
