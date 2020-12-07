package com.teleport.fwoj_backend.service;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface annService {
    //    查询公告列表 id,date,title,authorName
    String getAnnList(int page, int pre) throws JsonProcessingException;
    //查询公告详情
    String getAnnDetail(int id) throws JsonProcessingException;

    //查询公告列表和总数(Admin)
    String getAnnListAndNumAdmin(int page,int pre,String key,String token) throws JsonProcessingException;
    //按id查询公告详情(admin) id title content
    String getAnnDetailByIdAdmin(String token,int id) throws JsonProcessingException;

    //根据id删除公告
    String deleteAnnById(String token,int id)  throws JsonProcessingException;
    //改变公告的visible
    String changeAnnVisible(String token,int id) throws JsonProcessingException;
    //创建公告 date title content authorId
    String createAnn(String title,String content,String token) throws JsonProcessingException;
    //更新公告
    String updateAnn(String token,int id,String title,String content) throws JsonProcessingException;
}
