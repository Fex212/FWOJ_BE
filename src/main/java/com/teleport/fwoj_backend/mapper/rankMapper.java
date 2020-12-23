package com.teleport.fwoj_backend.mapper;

import com.teleport.fwoj_backend.pojo.rank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface rankMapper {
    //查询列表(页数,每页几条)
    List<rank> getRankList(@Param("start") int start, @Param("num") int num);
    //获取解题数排行至多十人的姓名
    List<String> getTop10Username();
    //获取解题数排行至多十人的解题数
    List<String> getTop10SolvedNum();
}
