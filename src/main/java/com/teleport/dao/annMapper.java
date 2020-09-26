package com.teleport.dao;

import com.teleport.pojo.ann;
import java.util.List;

public interface annMapper {

    //查询公告列表(页数,每页几条)
    List<ann> getAnnList(int page,int preNum);
}
