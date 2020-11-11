package com.teleport.fwoj_backend.mapper;

import com.teleport.fwoj_backend.pojo.ann;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface annMapper {

//    查询公告列表 id,date,title,authorName
    List<ann> getAnnList(@Param("start") int start, @Param("num") int num);
    //获取公告数量
    int getAnnSum();
//    按id查询公告详情 title,content,authorName,date
    ann getAnnDetail(int id);
//    查询公告列表(Admin) id title date visible authorName
    List<ann> getAnnListAdmin(@Param("start") int start, @Param("num") int num,@Param("key") String key);
//    查询公告总数(Admin)
    int getAnnSumAdmin();
    //根据id删除公告
    int deleteAnnById(int id);
    //根据id查询公告visible
    int getAnnVisibleById(int id);
    //根据id设置公告visible
    int setAnnVisibleById(int id,boolean visible);
    //创建公告 date title content authorId
    int createAnn(String date,String title,String content,int authorId);
    //通过id修改公告 title content
    int updateAnn(int id,String title,String content);
}
