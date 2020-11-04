package com.teleport.fwoj_backend.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.pojo.problem;
import com.teleport.fwoj_backend.pojo.user;
import com.teleport.fwoj_backend.service.problemService;
import com.teleport.fwoj_backend.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class adminContorller {


    @Autowired
    private problemService problemServiceObject;
    @Autowired
    private userService userServiceObject;

    //获取问题列表(Admin)
    //id title createTime visible authorName
    @RequestMapping(value = "/getProblemListAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemList(@RequestParam("page") int page, @RequestParam("pre") int pre,@RequestParam("token") String token,@RequestParam("key") String key)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            s.put("data",problemServiceObject.getProblemListAdmin(page,pre,token,key));
            s.put("num",problemServiceObject.getProblemSum());
            s.put("status",1);
        }
        else
            s.put("status",0);
        return  mapper.writeValueAsString(s);
    }


    //更改题目的visible
    @RequestMapping(value = "/changeProblemVisible",method = {RequestMethod.POST})
    @CrossOrigin
    public String changeProblemVisible(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            //可见
            if(problemServiceObject.getProblemVisibleById(id))
                if(problemServiceObject.setProblemVisibleById(id,false))
                    s.put("error","0");
                else
                    s.put("error","2");
            //不可见
            else
                if(problemServiceObject.setProblemVisibleById(id,true))
                    s.put("error","0");
                else
                    s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }
}
