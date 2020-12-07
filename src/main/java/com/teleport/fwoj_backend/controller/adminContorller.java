package com.teleport.fwoj_backend.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.pojo.contest;
import com.teleport.fwoj_backend.pojo.problem;
import com.teleport.fwoj_backend.service.annService;
import com.teleport.fwoj_backend.service.contestService;
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
    @Autowired
    private contestService contestServiceObject;
    @Autowired
    private annService annServiceObject;

    //获取系统信息面板
    @RequestMapping(value = "/getSystemInfo",method = {RequestMethod.GET})
    @CrossOrigin
    public String getSystemInfo(@RequestParam("token") String token)throws JsonProcessingException {
        return userServiceObject.getSystemInfo(token);
    }

    //    查询公告列表(Admin) id title date visible authorName
    @RequestMapping(value = "/getAnnListAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String getAnnListAdmin(@RequestParam("page") int page, @RequestParam("pre") int pre,
                                  @RequestParam("key") String key,@RequestParam("token") String token)
            throws JsonProcessingException {
            return annServiceObject.getAnnListAndNumAdmin(page,pre,key,token);
    }


    //按id查询公告详情(admin) id title content
    @RequestMapping(value = "/getAnnDetailByIdAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String getAnnDetailByIdAdmin(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {
        return annServiceObject.getAnnDetailByIdAdmin(token,id);
    }

    //根据id删除公告
    @RequestMapping(value = "/deleteAnnById",method = {RequestMethod.DELETE})
    @CrossOrigin
    public String deleteAnnById(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {
        return annServiceObject.deleteAnnById(token,id);
    }

    //根据id改变公告的visible
    @RequestMapping(value = "/changeAnnVisibleById",method = {RequestMethod.POST})
    @CrossOrigin
    public String changeAnnVisibleById(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {
        return annServiceObject.changeAnnVisible(token,id);
    }

    //创建公告 title content token
    @RequestMapping(value = "/createAnn",method = {RequestMethod.POST})
    @CrossOrigin
    public String createAnn(@RequestParam("token") String token,
                            @RequestParam("title") String title,@RequestParam("content") String content) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 删除失败
        return annServiceObject.createAnn(title,content,token);
    }

    //修改公告 id title content token
    @RequestMapping(value = "/updateAnn",method = {RequestMethod.POST})
    @CrossOrigin
    public String updateAnn(@RequestParam("token") String token,@RequestParam("id") int id,
                            @RequestParam("title") String title,@RequestParam("content") String content) throws JsonProcessingException {

        //error
        //0 正常 1 越权 2 失败
        return annServiceObject.updateAnn(token,id,title,content);
    }


    //获取问题列表(Admin)
    //id title createTime visible authorName
    @RequestMapping(value = "/getProblemListAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemList(@RequestParam("page") int page, @RequestParam("pre") int pre,@RequestParam("token") String token,@RequestParam("key") String key)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            s.put("data",problemServiceObject.getProblemListAdmin(page,pre,key));
            s.put("num",problemServiceObject.getProblemSumAdmin());
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
            if(problemServiceObject.createProblem(title,des,input,output,inputExample,outputExample,hint,userServiceObject.getUserNameByToken(token)))
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

    //按id查找题目详情(Admin)
    @RequestMapping(value = "/getProblemDetailAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String getProblemDetailAdmin(@RequestParam("token") String token,@RequestParam("id") int id)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            problem problemObject = problemServiceObject.getProblemDetailAdmin(id);
//            System.out.println(problemObject);
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

    //获取比赛列表(Admin)
    //id title des createTime visible authorName
    @RequestMapping(value = "/getContestListAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestListAdmin(@RequestParam("page") int page, @RequestParam("pre") int pre,@RequestParam("token") String token,@RequestParam("key") String key)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            s.put("data",contestServiceObject.getContestListAdmin(page,pre,key));
            s.put("num",contestServiceObject.getContestSumAdmin());
            s.put("error",0);
        }
        else
            s.put("error",1);
        return  mapper.writeValueAsString(s);
    }

    //更改比赛可见性
    @RequestMapping(value = "/changeContestVisible",method = {RequestMethod.POST})
    @CrossOrigin
    public String changeContestVisible(@RequestParam("token") String token,@RequestParam("id") int id)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(contestServiceObject.contestVisibleChanged(id))
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return  mapper.writeValueAsString(s);
    }

    //根据id删除比赛
    @RequestMapping(value = "/deleteContestById",method = {RequestMethod.DELETE})
    @CrossOrigin
    public String deleteContestById(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {

        //error
        //0 正常 1 越权 2 删除失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(contestServiceObject.deleteContestById(id))
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

    // 按id查询比赛详情(Admin) id,title,des,problemList,startTime,endTime
    @RequestMapping(value = "/getContestDetailByIdAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String getContestDetailByIdAdmin(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            contest contestObject = contestServiceObject.getContestDetailByIdAdmin(id);
            if(contestObject != null)
            {
                s.put("data",contestObject);
                s.put("error","0");
            }
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }


    //创建比赛 title des problemList startTime endTime  authorName
    @RequestMapping(value = "/createContest",method = {RequestMethod.POST})
    @CrossOrigin
    public String createContest(@RequestParam("token") String token,
                                @RequestParam("title") String title,@RequestParam("des") String des, @RequestParam("problemList") String problemList,
                                @RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(contestServiceObject.createContest(title,des,problemList,startTime,endTime,false,userServiceObject.getUserNameByToken(token)))
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

    //根据id更新比赛信息 title title startTime endTime
    //按id更新题目信息
    @RequestMapping(value = "/editContestById",method = {RequestMethod.POST})
    @CrossOrigin
    public String editContestById(@RequestParam("token") String token,@RequestParam("title") String title,@RequestParam("des") String des,
                              @RequestParam("problemList") String problemList,@RequestParam("startTime") String startTime,
                                  @RequestParam("endTime") String endTime, @RequestParam("id") int id) throws JsonProcessingException {

        // System.out.println(token+"\n"+title+"\n"+des+"\n"+problemList+"\n"+startTime+"\n"+endTime+"\n"+id+"\n");
        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(contestServiceObject.editContestById(title,des,problemList,startTime,endTime,id))
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

}
