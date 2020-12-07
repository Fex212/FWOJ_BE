package com.teleport.fwoj_backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.pojo.user;
import com.teleport.fwoj_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class userController {
    @Autowired
    private userService userServiceObject;

    //登陆
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    @CrossOrigin
    public String login(@RequestParam("username") String username,
                        @RequestParam("passwd") String passwd) throws JsonProcessingException {
        return userServiceObject.login(username,passwd);
    }

    //根据token查询用户名
    @RequestMapping(value = "/getUserName",method = {RequestMethod.POST})
    @CrossOrigin
    public String getUserNameByToken(@RequestParam("token") String token) throws JsonProcessingException
    {
        return userServiceObject.getUserNameByToken(token);
    }

    //根据token查询用户Id
    @RequestMapping(value = "/getUserId",method = {RequestMethod.POST})
    @CrossOrigin
    public String getUserIdByToken(@RequestParam("token") String token) throws JsonProcessingException
    {
        return userServiceObject.getUserIdByToken(token);
    }

    //根据token查询用户Type
    @RequestMapping(value = "/getUserType",method = {RequestMethod.POST})
    @CrossOrigin
    public String getUserTypeByToken(@RequestParam("token") String token) throws JsonProcessingException
    {
        return userServiceObject.getUserTypeByToken(token);
    }

    //传入email username passwd 注册
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    @CrossOrigin
    public String register(@RequestParam("email") String email,@RequestParam("username") String username,@RequestParam("passwd") String passwd)
            throws JsonProcessingException
    {
        return userServiceObject.register(email,username,passwd);
    }

    //获取用户列表
    @RequestMapping(value = "/getUserList",method = {RequestMethod.GET})
    @CrossOrigin
    public String getUserList(@RequestParam("page") int page,@RequestParam("pre") int pre,@RequestParam("token") String token,@RequestParam("key") String key) throws JsonProcessingException {
        return userServiceObject.getUserList(page,pre,key,token);
    }

    //根据token查询是否为管理员
    @RequestMapping(value = "/tokenIsAdmin",method = {RequestMethod.GET})
    @CrossOrigin
    public String tokenIsAdmin(@RequestParam("token") String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            s.put("result","1");
        }
        else
            s.put("result","0");
        return mapper.writeValueAsString(s);
    }

    //根据管理员的token和用户id查询用户详细信息
    //根据token查询是否为管理员
    @RequestMapping(value = "/getUserDetailById",method = {RequestMethod.GET})
    @CrossOrigin
    public String getUserDetailById(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            s.put("result","1");
            s.put("userDetail",userServiceObject.getUserDetailById(id));
        }
        else
            s.put("result","0");
        return mapper.writeValueAsString(s);
    }

    //更新用户信息
    //根据管理员的token和用户id查询用户详细信息(带密码)
    @RequestMapping(value = "/updateUser",method = {RequestMethod.POST})
    @CrossOrigin
    public String updateUser(@RequestParam("token") String token, @RequestParam("email") String email, @RequestParam("username") String username,
                             @RequestParam("type") String type, @RequestParam("des") String des, @RequestParam("passwd") String passwd,
                                @RequestParam("id") int id) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        int emailNumExcept = userServiceObject.getEmailNumExpect(email,id);
        int usernameNumExcept = userServiceObject.getUsernameNumExpect(username,id);
        //error:1 email exist 2 username exist
        //3 format error 4 sql error
        //5 permission error
        //判断是否是admin权限
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(emailNumExcept != 0)
                s.put("error","1");
            else if(usernameNumExcept  != 0)
                s.put("error","2");
            else if(username.length() > 10 || username.length() < 2 || passwd.length()>35 || email.length() > 30 || (!type.equals("admin") && !type.equals("user")))
                s.put("error","3");
            else
            {
                boolean r;
                r = userServiceObject.editUserDetail(email,username,type,des,passwd,id);
                if(r)
                    s.put("error","0");
                else
                    s.put("error","4");
            }
        }
        else
            s.put("error","5");
        return mapper.writeValueAsString(s);
    }

    //更新用户信息
    //根据管理员的token和用户id查询用户详细信息(带密码)
    @RequestMapping(value = "/updateUserWithoutPasswd",method = {RequestMethod.POST})
    @CrossOrigin
    public String updateUserWithoutPasswd(@RequestParam("token") String token, @RequestParam("email") String email, @RequestParam("username") String username,
                             @RequestParam("type") String type, @RequestParam("des") String des, @RequestParam("id") int id) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        int emailNumExcept = userServiceObject.getEmailNumExpect(email,id);
        int usernameNumExcept = userServiceObject.getUsernameNumExpect(username,id);
        //error:1 email exist 2 username exist
        //3 format error 4 sql error
        //5 permission error
        //判断是否是admin权限
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(emailNumExcept != 0)
                s.put("error","1");
            else if(usernameNumExcept  != 0)
                s.put("error","2");
            else if(username.length() > 10 || username.length() < 2  || email.length() > 30 || (!type.equals("admin") && !type.equals("user")))
                s.put("error","3");
            else
            {
                boolean r;
                r = userServiceObject.editUserDetailWithoutPasswd(email,username,type,des,id);
                if(r)
                    s.put("error","0");
                else
                    s.put("error","4");
            }
        }
        else
            s.put("error","5");
        return mapper.writeValueAsString(s);
    }

    //通过id删除用户
    //根据token查询是否为管理员
    @RequestMapping(value = "/deleteUser",method = {RequestMethod.DELETE})
    @CrossOrigin
    public String deleteUser(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {


        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            if(userServiceObject.deleteUser(id))
                s.put("error","0");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    //更改用户的available
    @RequestMapping(value = "/changeUserAvailable",method = {RequestMethod.POST})
    @CrossOrigin
    public String changeUserAvailable (@RequestParam("token") String token,@RequestParam("username") String username) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userServiceObject.tokenIsAdmin(token))
        {
            //可用
            if(userServiceObject.getAvailableByUsername(username))
                userServiceObject.setAvailableByUsername(username,false);
            //不可用
            else
                userServiceObject.setAvailableByUsername(username,true);
            s.put("error","0");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

    //根据token获取个人信息设置所需数据
    @RequestMapping(value = "/getUserPersonInfo",method = {RequestMethod.POST})
    @CrossOrigin
    public String getUserPersonInfo(@RequestParam("token") String token) throws JsonProcessingException {
        return userServiceObject.getUserPersonInfo(token);
    }
    //更新个人设置中的sign,site,github
    @RequestMapping(value = "/updateUserPersonInfo", method = {RequestMethod.POST})
    @CrossOrigin
    public String updateUserPersonInfo(@RequestParam("token") String token,@RequestParam("sign") String sign,@RequestParam("site") String site,@RequestParam("github") String github) throws JsonProcessingException {
        return userServiceObject.updateUserPersonInfo(token,sign,site,github);
    }
    //个人设置更新密码
    @RequestMapping(value = "updatePasswordByPrePasswd",method = {RequestMethod.POST})
    @CrossOrigin
    public String updatePasswordByPrePassword(@RequestParam("token") String token ,
                                              @RequestParam("oldpasswd") String oldpasswd,@RequestParam("passwd") String passwd) throws JsonProcessingException {
        return userServiceObject.updatePasswordByPrePassword(token,oldpasswd,passwd);
    }

    //根据username获取个人资料卡所需数据
    @RequestMapping(value = "/getUserCardInfo/{username}",method = {RequestMethod.GET})
    @CrossOrigin
    public String getUserCardInfo(@PathVariable("username") String username) throws JsonProcessingException {
        return userServiceObject.getUserCardInfo(username);
    }


}
