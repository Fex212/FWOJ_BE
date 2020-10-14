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
    //方法数为多个时，要加入@Param注解
    List<contest> getContestList(@Param("start") int start, @Param("num") int num);
    //获取比赛总数
    int getContestSum();
    contest getContestDetail(int id);
}
