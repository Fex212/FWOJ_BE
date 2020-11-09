package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.contest;

import java.text.ParseException;
import java.util.List;

public interface contestService {
    //方法数为多个时，要加入@Param注解
    List<contest> getContestList(int page, int pre) throws ParseException;
    //查询比赛列表(admin)
    List<contest> getContestListAdmin(int page, int pre,String key);
    //获取比赛总数
    int getContestSum();
    //获取比赛总数(admin)
    int getContestSumAdmin();
    //按id查询比赛详情
    contest getContestDetail(int id) throws ParseException;
    //改变比赛可见性
    boolean contestVisibleChanged(int id);
    //根据id删除比赛
    boolean deleteContestById(int id);
    //创建比赛 title des problemList startTime endTime visible authorName
    boolean createContest(String title,String des,String problemList,String startTime,String endTime,boolean visible,String authorName);
    //  update title des problemList startTime endTime
    boolean editContestById(String title,String des,String problemList,String startTime,String endTime,int id);

}
