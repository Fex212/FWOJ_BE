package com.teleport.fwoj_backend.mapper;

import com.teleport.fwoj_backend.pojo.problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface problemMapper {
    //查询问题列表(页数,每页几条)
    List<problem> getProblemList(@Param("start") Integer start,@Param("num") Integer num);
    //查询问题总记录数
    Integer getProblemSum();
    problem getProblemDetail(int id);
}
