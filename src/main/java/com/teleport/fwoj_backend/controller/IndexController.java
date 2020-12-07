package com.teleport.fwoj_backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.pojo.*;
import com.teleport.fwoj_backend.service.annService;
import com.teleport.fwoj_backend.service.contestService;
import com.teleport.fwoj_backend.service.problemService;
import com.teleport.fwoj_backend.service.stateService;
import com.teleport.fwoj_backend.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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
        ObjectMapper mapper = new ObjectMapper();
        ;
        List<state> list = stateServiceObject.getStateList(page, pre);
        int total = stateServiceObject.getStateSum();
        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("status", 200);
        return mapper.writeValueAsString(s);
    }

    //获取状态详情
    @RequestMapping(value = "/getStateDetail", method = {RequestMethod.GET})
    @CrossOrigin
    public String getStateDetail(@RequestParam("id") int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        s.put("data", stateServiceObject.getStateDetail(id));
        return mapper.writeValueAsString(s);
    }

    //提交代码 题目
    @RequestMapping(value = "/submitProblemCode", method = {RequestMethod.POST})
    @CrossOrigin
    public String submitProblemCode(@RequestParam("code") String code, @RequestParam("problemId") int problemId,
                                    @RequestParam("token") String token, @RequestParam("language") String language) throws JsonProcessingException {
        return stateServiceObject.addState(code,problemId,token,language);
    }

    //error -1 文件为空 -2 后端异常
    @RequestMapping("/uploadAvatar")
    @CrossOrigin
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @RequestParam("token") String token) throws JsonProcessingException {
        return userServiceObject.uploadAvatar(file,token);
    }

    //error -1 用户不存在
    @RequestMapping(value = "/getAvatar",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    @CrossOrigin
    public  byte[] getAvatarUrl(@RequestParam("username") String username) throws IOException {
        return userServiceObject.getAvatarUrl(username);
    }

}

