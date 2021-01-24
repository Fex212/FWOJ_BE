package com.teleport.fwoj_backend.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface problemService {
    //查询列表(页数,每页几条),返回List
    String getProblemList(int page,int pre,String token) throws JsonProcessingException;
    //查询问题详情
    String getProblemDetailById(int id) throws JsonProcessingException;

    //获取问题列表(Admin) id title createTime visible authorName
    String getProblemListAdmin(int page,int pre,String key,String token) throws JsonProcessingException;
    //查询问题详情(Admin)
    String getProblemDetailAdmin(String token,int id) throws JsonProcessingException;
    //更改问题可见性
    String changeProblemVisible(String token,int id) throws JsonProcessingException;

    //根据id删除问题
    String deleteProblemById(String token,int id) throws JsonProcessingException;

    //创建问题
    String createProblem(String token,String title,String des,String input,String output,String inputExample,String outputExample,String hint) throws JsonProcessingException;
    //根据id编辑问题id
    String editProblem(String token,String title,String des,String input,String output,String inputExample,String outputExample,
                    String hint,int id) throws JsonProcessingException;

    //按id上传题目测试样例
    String uploadTestCaseById(MultipartFile file, String token, int id) throws JsonProcessingException;

    //按id下载题目测试样例
    void downloadTestCaseById(String token, int id, HttpServletResponse res) throws IOException;

    //按id查询题目是否有测试样例
    String isTestCaseExistById(String token,int id) throws JsonProcessingException;
}
