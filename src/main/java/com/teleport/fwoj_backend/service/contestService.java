package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.text.ParseException;

public interface contestService {
    //查询比赛列表(user)
    String getContestList(int page, int pre) throws ParseException, JsonProcessingException;
    //查询比赛列表(admin)
    String getContestListAdmin(String token,int page, int pre,String key) throws JsonProcessingException;
    //按id查询比赛详情
    String getContestDetail(int id) throws ParseException, JsonProcessingException;
    //按id查询详情(Admin)
    String getContestDetailByIdAdmin(String token,int id) throws JsonProcessingException;
    //改变比赛可见性
    String contestVisibleChanged(String token,int id) throws JsonProcessingException;
    //根据id删除比赛
    String deleteContestById(String token,int id) throws JsonProcessingException;
    //创建比赛 title des problemList startTime endTime visible authorName
    String createContest(String token,String title,String des,String problemList,String startTime,String endTime,boolean visible,String authorName) throws JsonProcessingException;
    //  update title des problemList startTime endTime
    String editContestById(String token,String title,String des,String problemList,String startTime,String endTime,int id) throws JsonProcessingException;

}
