package com.teleport.fwoj_backend.mapper;

import com.teleport.fwoj_backend.pojo.ann;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface annMapper {

    //查询公告列表(页数,每页几条)
    //方法数为多个时，要加入@Param注解
    List<ann> getAnnList(@Param("start") int start, @Param("num") int num);
    int getAnnSum();
    ann getAnnDetail(int id);
}
