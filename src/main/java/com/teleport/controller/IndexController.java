package com.teleport.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.pojo.ann;
import com.teleport.pojo.problem;
import com.teleport.service.annService;
import com.teleport.service.problemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {


    @Autowired
    @Qualifier("annServiceImpl")
    private annService annServiceObject;
    @Autowired
    @Qualifier("problemServiceImpl")
    private problemService problemServiceObject;

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

    //文件上传
}
