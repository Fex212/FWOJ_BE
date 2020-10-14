package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.contest;

import java.text.ParseException;
import java.util.List;

public interface contestService {
    //查询比赛列表(页数,每页几条)
    //方法数为多个时，要加入@Param注解
    List<contest> getContestList(int page, int pre) throws ParseException;
    //获取比赛总数
    int getContestSum();
    contest getContestDetail(int id);
}
