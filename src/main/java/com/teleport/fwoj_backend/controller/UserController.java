package com.teleport.fwoj_backend.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return userServiceObject.tokenIsAdmin(token);
    }

    //根据管理员的token和用户id查询用户详细信息
    @RequestMapping(value = "/getUserDetailById",method = {RequestMethod.GET})
    @CrossOrigin
    public String getUserDetailById(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {
        return userServiceObject.getUserDetailById(token,id);
    }

    //更新用户Admin
    @RequestMapping(value = "/updateUser",method = {RequestMethod.POST})
    @CrossOrigin
    public String updateUser(@RequestParam("token") String token, @RequestParam("email") String email, @RequestParam("username") String username,
                             @RequestParam("type") String type, @RequestParam("passwd") String passwd,
                                @RequestParam("id") int id) throws JsonProcessingException {
        return userServiceObject.editUserDetail(token,email,username,type,passwd,id);
    }

    //更新用户信息不带密码Admin
    @RequestMapping(value = "/updateUserWithoutPasswd",method = {RequestMethod.POST})
    @CrossOrigin
    public String updateUserWithoutPasswd(@RequestParam("token") String token, @RequestParam("email") String email, @RequestParam("username") String username,
                             @RequestParam("type") String type, @RequestParam("id") int id) throws JsonProcessingException {

        return userServiceObject.editUserDetailWithoutPasswd(token,email,username,type,id);
    }

    //通过id删除用户
    @RequestMapping(value = "/deleteUser",method = {RequestMethod.DELETE})
    @CrossOrigin
    public String deleteUser(@RequestParam("token") String token,@RequestParam("id") int id) throws JsonProcessingException {
        return userServiceObject.deleteUser(token,id);
    }

    //更改用户的available
    @RequestMapping(value = "/changeUserAvailable",method = {RequestMethod.POST})
    @CrossOrigin
    public String changeUserAvailable (@RequestParam("token") String token,@RequestParam("username") String username) throws JsonProcessingException {
        return userServiceObject.changeUserAvailable(token,username);
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
