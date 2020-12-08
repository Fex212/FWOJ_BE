package com.teleport.fwoj_backend.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.teleport.fwoj_backend.service.annService;
import com.teleport.fwoj_backend.service.contestService;
import com.teleport.fwoj_backend.service.problemService;
import com.teleport.fwoj_backend.service.stateService;
import com.teleport.fwoj_backend.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {

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
    @RequestMapping(value = "/getAnnList", method = {RequestMethod.GET})
    @CrossOrigin
    public String getAnnouncementList(@RequestParam("page") int page, @RequestParam("pre") int pre) throws JsonProcessingException {
        return annServiceObject.getAnnList(page,pre);
    }

    //获取公告详情
    @RequestMapping(value = "/getAnnDetail", method = {RequestMethod.GET})
    @CrossOrigin
    public String getAnnDetail(@RequestParam("id") int id) throws JsonProcessingException {
        return annServiceObject.getAnnDetail(id);
    }

    //获取问题列表
    @RequestMapping(value = "/getProblemList", method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemList(@RequestParam("page") int page, @RequestParam("pre") int pre) throws JsonProcessingException {
        return problemServiceObject.getProblemList(page,pre);
    }

    //获取问题详情
    @RequestMapping(value = "/getProblemDetail", method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemDetail(@RequestParam("id") int id) throws JsonProcessingException {
        return problemServiceObject.getProblemDetailById(id);
    }

    //获取比赛列表
    @RequestMapping(value = "/getContestList", method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestList(@RequestParam("page") int page, @RequestParam("pre") int pre) throws JsonProcessingException, ParseException {
        return contestServiceObject.getContestList(page,pre);
    }

    //获取比赛详情
    @RequestMapping(value = "/getContestDetail", method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestDetail(@RequestParam("id") int id) throws JsonProcessingException, ParseException {
        return contestServiceObject.getContestDetail(id);
    }

    //获取状态列表
    @RequestMapping(value = "/getStateList", method = {RequestMethod.GET})
    @CrossOrigin
    public String getStateList(@RequestParam("page") int page, @RequestParam("pre") int pre) throws JsonProcessingException {
        return stateServiceObject.getStateList(page,pre);
    }

    //获取状态详情
    @RequestMapping(value = "/getStateDetail", method = {RequestMethod.GET})
    @CrossOrigin
    public String getStateDetail(@RequestParam("id") int id) throws JsonProcessingException {
        return stateServiceObject.getStateDetail(id);
    }

    //提交代码 题目
    @RequestMapping(value = "/submitProblemCode", method = {RequestMethod.POST})
    @CrossOrigin
    public String submitProblemCode(@RequestParam("code") String code, @RequestParam("problemId") int problemId,
                                    @RequestParam("token") String token, @RequestParam("language") String language) throws JsonProcessingException {
        return stateServiceObject.addState(code,problemId,token,language);
    }

}

