package com.teleport.fwoj_backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.pojo.ann;
import com.teleport.fwoj_backend.pojo.contest;
import com.teleport.fwoj_backend.pojo.problem;
import com.teleport.fwoj_backend.service.annService;
import com.teleport.fwoj_backend.service.contestService;
import com.teleport.fwoj_backend.service.problemService;
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

    @RequestMapping("/hello")
    public String hello()
    {
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

//  获取比赛列表
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

    //获取问题详情
    @RequestMapping(value = "/getContestDetail",method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestDetail(@RequestParam("id") int id) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        s.put("data",contestServiceObject.getContestDetail(id));
        return  mapper.writeValueAsString(s);
    }
}
