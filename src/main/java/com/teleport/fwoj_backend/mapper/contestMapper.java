package com.teleport.fwoj_backend.mapper;

import com.teleport.fwoj_backend.pojo.contest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface contestMapper {
    //查询比赛列表(页数,每页几条)
    List<contest> getContestList(@Param("start") int start, @Param("num") int num);
    //查询比赛列表(admin)
    List<contest> getContestListAdmin(@Param("start") int start, @Param("num") int num,String key);
    //获取比赛总数
    int getContestSum();
    //获取比赛总数(admin)
    int getContestSumAdmin();
    //按id查询比赛详情
    contest getContestDetail(int id);
    //按id查询比赛可见性
    int getContestVisibleById(int id);
    //根据id更改比赛可见性
    int setContestVisibleById(int id,boolean visible);
    //根据id删除比赛
    int deleteContestById(int id);
}
