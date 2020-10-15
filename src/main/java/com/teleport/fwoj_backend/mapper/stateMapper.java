package com.teleport.fwoj_backend.mapper;
import com.teleport.fwoj_backend.pojo.state;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface stateMapper{
    //查询列表(页数,每页几条)
    List<state> getStateList(@Param("start") int start, @Param("num") int num);
    //查询总记录数
    int getStateSum();
    //按id查询
    state getStateDetail(int id);

}
