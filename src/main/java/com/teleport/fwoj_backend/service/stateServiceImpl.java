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
    public String getStateList(int page, int pre) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int start = pre * (page - 1);
        int num = pre;
        List<state> list = stateMapperObject.getStateList(start,num);
        int total = stateMapperObject.getStateSum();
        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("error", "0");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String  getStateDetail(int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        s.put("data", stateMapperObject.getStateDetail(id));
        return mapper.writeValueAsString(s);
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
