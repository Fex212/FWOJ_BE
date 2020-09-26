package com.teleport.service;

import com.teleport.pojo.ann;
import java.util.List;

public interface annService {

    //查询公告列表(页数,每页几条),返回List
    List<ann> getAnnList(int page,int preNum);
}
