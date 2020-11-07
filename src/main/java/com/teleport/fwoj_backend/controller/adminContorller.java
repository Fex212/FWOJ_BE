package com.teleport.fwoj_backend.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.pojo.problem;
import com.teleport.fwoj_backend.service.problemService;
import com.teleport.fwoj_backend.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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


    //根据id删除题目
    @RequestMapping(value = "/deleteProblemById",method = {RequestMethod.DELETE})
    @CrossOrigin
    public String deleteProblemById(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {

        //error
        //0 正常 1 越权 2 删除失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(problemServiceObject.deleteProblemById(id))
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

//    创建题目
    @RequestMapping(value = "/createProblem",method = {RequestMethod.POST})
    @CrossOrigin
    public String createProblem(@RequestParam("token") String token,@RequestParam("title") String title,@RequestParam("des") String des,
                                @RequestParam("input") String input,@RequestParam("output") String output,
                                @RequestParam("inputExample") String inputExample,@RequestParam("outputExample") String outputExample,
                                @RequestParam("hint") String hint) throws JsonProcessingException {

        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(problemServiceObject.createProblem(title,des,input,output,inputExample,outputExample,hint,userServiceObject.getUserName(token)))
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

    //按id查找题目详情
    @RequestMapping(value = "/getProblemDetailAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemDetailAdmin(@RequestParam("token") String token,@RequestParam("id") int id)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            problem problemObject = problemServiceObject.getProblemDetail(id);
            if(problemObject != null)
            {
            s.put("error","0");
            s.put("data",problemObject);
            }
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return  mapper.writeValueAsString(s);
    }

    //按id更新题目信息
    @RequestMapping(value = "/editProblem",method = {RequestMethod.POST})
    @CrossOrigin
    public String editProblem(@RequestParam("token") String token,@RequestParam("title") String title,@RequestParam("des") String des,
                                @RequestParam("input") String input,@RequestParam("output") String output,
                                @RequestParam("inputExample") String inputExample,@RequestParam("outputExample") String outputExample,
                                @RequestParam("hint") String hint,@RequestParam("id") int id) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(problemServiceObject.editProblem(title,des,input,output,inputExample,outputExample,hint,id))
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

}
