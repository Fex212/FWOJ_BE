package com.teleport.fwoj_backend.mapper;
import com.teleport.fwoj_backend.pojo.state;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface stateMapper{
    //查询列表(页数,每页几条)
    List<state> getStateList(@Param("start") int start, @Param("num") int num);
    //查询总记录数
    int getStateSum();
    //按id查询state
    state getStateDetail(int id);
    //插入一条
    int addState(int problemId, int authorId, Date date,String language,String code);

    //查询Pending列表
    List<state> getPendingList();

    //更新state
    int updateState(int id , String state,String compileInfo);

    //设置timeCost
    int setTimeCost(int id , int timeCost);

    //设置memoryCost
    int setMemoryCost(int id,int memoryCost);


}
