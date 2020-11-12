package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.ann;
import java.util.List;

public interface annService {

    //    查询公告列表 id,date,title,authorName
    List<ann> getAnnList(int page, int pre);
    //查询公告条数
    int getAnnSum();
    //    按id查询公告详情 title,content,authorName,date
    ann getAnnDetail(int id);
    //按id查询公告详情(admin) id title content
    ann getAnnDetailByIdAdmin(int id);
    //    查询公告列表(Admin) id title date visible authorName
    List<ann> getAnnListAdmin(int page,int pre,String key);
    //    查询公告总数(Admin)
    int getAnnSumAdmin();
    //根据id删除公告
    boolean deleteAnnById(int id);
    //改变公告的visible
    boolean changeAnnVisible(int id);
    //创建公告 date title content authorId
    boolean createAnn(String date,String title,String content,int authorId);
    boolean updateAnn(int id,String title,String content);
}
