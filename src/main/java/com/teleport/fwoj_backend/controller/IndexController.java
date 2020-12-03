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
        ObjectMapper mapper = new ObjectMapper();

        List<ann> list = annServiceObject.getAnnList(page, pre);
        int total = annServiceObject.getAnnSum();

        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("status", 200);
        return mapper.writeValueAsString(s);
    }

    //获取公告详情
    @RequestMapping(value = "/getAnnDetail", method = {RequestMethod.GET})
    @CrossOrigin
    public String getAnnDetail(@RequestParam("id") int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        ann annObject = annServiceObject.getAnnDetail(id);

        HashMap s = new HashMap();
        s.put("data", annObject);
        if (annObject != null)
            s.put("error", "0");
        return mapper.writeValueAsString(s);
    }

    //获取问题列表
    @RequestMapping(value = "/getProblemList", method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemList(@RequestParam("page") int page, @RequestParam("pre") int pre) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<problem> list = problemServiceObject.getProblemList(page, pre);
        int total = problemServiceObject.getProblemSum();
        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("status", 200);
        return mapper.writeValueAsString(s);
    }

    //获取问题详情
    @RequestMapping(value = "/getProblemDetail", method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemDetail(@RequestParam("id") int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        problem problemObject = problemServiceObject.getProblemDetail(id);

        HashMap s = new HashMap();
        if (problemObject != null) {
            s.put("data", problemObject);
            s.put("error", "0");
        }
        //对象为null
        else
            s.put("error", "1");
        return mapper.writeValueAsString(s);
    }

    //获取比赛列表
    @RequestMapping(value = "/getContestList", method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestList(@RequestParam("page") int page, @RequestParam("pre") int pre) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();

        List<contest> list = contestServiceObject.getContestList(page, pre);
        int total = contestServiceObject.getContestSum();
        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("status", 200);
        return mapper.writeValueAsString(s);
    }

    //获取比赛详情
    @RequestMapping(value = "/getContestDetail", method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestDetail(@RequestParam("id") int id) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        contest contestObject = contestServiceObject.getContestDetail(id);
        s.put("data", contestObject);
        if (contestObject != null)
            s.put("error", "0");
        return mapper.writeValueAsString(s);
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
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        //token不合法
        if (userServiceObject.getUserName(token) == null) {
            s.put("error", "1");
        } else {
            //提交到state表
            Date date = new Date();
            int authorId = userServiceObject.getUserId(token);
            boolean isVisible = problemServiceObject.getProblemVisibleById(problemId);
//            System.out.println(problemId);
//            System.out.println(isVisible);
            if (isVisible && stateServiceObject.addState(problemId, authorId, date, language, code))
                s.put("error", "0");
            else
                s.put("error", "2");
        }
        return mapper.writeValueAsString(s);
    }

    //error -1 文件为空 -2 后端异常
    @RequestMapping("/uploadAvatar")
    @CrossOrigin
    public String singleFileUpload(@RequestParam("avatar") MultipartFile file, @RequestParam("token") String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        String UPLOAD_FOLDER = "./uploadFolder/avatar/";
        String username = userServiceObject.getUserName(token);
        if (username != null) {
            String kzm = ".jpg";
            if (Objects.isNull(file)) {
                s.put("error", "-1");
                return mapper.writeValueAsString(s);
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER + username + kzm);
                if (!Files.isWritable(path)) {
                    Files.createDirectories(Paths.get(UPLOAD_FOLDER));
                }
                Files.write(path, bytes);
                s.put("error", "0");
            } catch (IOException e) {
                s.put("error", "-2");
            }
        }
        else
            s.put("error", "-3");
        return mapper.writeValueAsString(s);
    }

    //error -1 用户不存在
    @RequestMapping(value = "/getAvatar",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    @CrossOrigin
    public  byte[] getAvatarUrl(@RequestParam("token") String token) throws IOException {

        String username = userServiceObject.getUserName(token);
        byte[] bytes;
        FileInputStream inputStream;
        if (username != null)
        {
            boolean flag = true;
            File avatarFile = new File("./uploadFolder/avatar/" + username + ".jpg");
            if (!avatarFile.exists()) {
                flag = false;
            }
            //存在,返回对应头像
            if (flag) {
                inputStream = new FileInputStream(avatarFile);
                bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
            }
            //不存在,返回默认头像
            else {
                inputStream = new FileInputStream("./uploadFolder/defaultAvatar.jpg");
                bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
            }
            return bytes;
        }
        else
        {
            inputStream = new FileInputStream("./uploadFolder/defaultAvatar.jpg");
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }
    }

}

