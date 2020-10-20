package com.teleport.fwoj_backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.pojo.ann;
import com.teleport.fwoj_backend.pojo.contest;
import com.teleport.fwoj_backend.pojo.problem;
import com.teleport.fwoj_backend.pojo.state;
import com.teleport.fwoj_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private annService annServiceObject;
    @Autowired
    private problemService problemServiceObject;
    @Autowired
    private contestService contestServiceObject;
    @Autowired
    private stateService stateServiceObject;
    @Autowired
    private userService userServiceObject;

    @RequestMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    //获取公告列表
    @RequestMapping(value = "/getAnnList",method = {RequestMethod.GET})
    @CrossOrigin
    public String getAnnouncementList(@RequestParam("page") int page,@RequestParam("pre") int pre)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<ann> list = annServiceObject.getAnnList(page,pre);
        int total  = annServiceObject.getAnnSum();

        HashMap s = new HashMap();
        s.put("data",list);
        s.put("total",total);
        s.put("status",200);
        return  mapper.writeValueAsString(s);
    }

    //获取公告详情
    @RequestMapping(value = "/getAnnDetail",method = {RequestMethod.GET})
    @CrossOrigin
    public String getAnnDetail(@RequestParam("id") int id)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        ann annObject =  annServiceObject.getAnnDetail(id);

        HashMap s = new HashMap();
        s.put("data",annObject);
        return  mapper.writeValueAsString(s);
    }

    //获取问题列表
    @RequestMapping(value = "/getProblemList",method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemList(@RequestParam("page") int page,@RequestParam("pre") int pre)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<problem> list = problemServiceObject.getProblemList(page,pre);
        int total  = problemServiceObject.getProblemSum();
        HashMap s = new HashMap();
        s.put("data",list);
        s.put("total",total);
        s.put("status",200);
        return  mapper.writeValueAsString(s);
    }

    //获取问题详情
    @RequestMapping(value = "/getProblemDetail",method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemDetail(@RequestParam("id") int id)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        problem problemObject =  problemServiceObject.getProblemDetail(id);

        HashMap s = new HashMap();
        s.put("data",problemObject);
        return  mapper.writeValueAsString(s);
    }

    //获取比赛列表
    @RequestMapping(value = "/getContestList",method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestList(@RequestParam("page") int page,@RequestParam("pre") int pre) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();

        List<contest> list = contestServiceObject.getContestList(page,pre);
        int total  = contestServiceObject.getContestSum();
        HashMap s = new HashMap();
        s.put("data",list);
        s.put("total",total);
        s.put("status",200);
        return  mapper.writeValueAsString(s);
    }

    //获取比赛详情
    @RequestMapping(value = "/getContestDetail",method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestDetail(@RequestParam("id") int id) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        s.put("data",contestServiceObject.getContestDetail(id));
        return  mapper.writeValueAsString(s);
    }

    //获取状态列表
    @RequestMapping(value = "/getStateList",method = {RequestMethod.GET})
    @CrossOrigin
    public String getStateList(@RequestParam("page") int page,@RequestParam("pre") int pre) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<state> list = stateServiceObject.getStateList(page,pre);
        int total  = stateServiceObject.getStateSum();
        HashMap s = new HashMap();
        s.put("data",list);
        s.put("total",total);
        s.put("status",200);
        return  mapper.writeValueAsString(s);
    }

    //获取比赛详情
    @RequestMapping(value = "/getStateDetail",method = {RequestMethod.GET})
    @CrossOrigin
    public String getStateDetail(@RequestParam("id") int id) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        s.put("data",stateServiceObject.getStateDetail(id));
        return  mapper.writeValueAsString(s);
    }
    //验证登陆是否成功
    @RequestMapping(value = "/loginCheck",method = {RequestMethod.GET})
    @CrossOrigin
    public String loginCheck(@RequestParam("username") String username,@RequestParam("passwd") String passwd) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.loginCheck(username,passwd) == 1)
        {
            s.put("status","1");
            s.put("token",userServiceObject.createToken(username));
        }
        else
            s.put("status","0");
        return mapper.writeValueAsString(s);
    }
}