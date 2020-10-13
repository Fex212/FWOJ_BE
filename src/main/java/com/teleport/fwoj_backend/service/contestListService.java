package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.contestList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface contestListService {
    //查询比赛列表(页数,每页几条)
    //方法数为多个时，要加入@Param注解
    List<contestList> getContestList(Integer start,Integer num);
    //获取比赛总数
    Integer getContestSum();
}
