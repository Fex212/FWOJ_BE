package com.teleport.fwoj_backend.mapper;

import com.teleport.fwoj_backend.pojo.contestList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface contestListMapper {

    //查询比赛列表(页数,每页几条)
    //方法数为多个时，要加入@Param注解
    List<contestList> getContestList(@Param("start") Integer start, @Param("num") Integer num);
    //获取比赛总数
    Integer getContestSum();
}
