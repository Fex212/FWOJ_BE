package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.pojo.ann;
import java.util.List;

public interface annService {

    //查询公告列表(页数,每页几条),返回List
    List<ann> getAnnList(int page, int pre);
    //查询公告条数
    int getAnnSum();
    ann getAnnDetail(int id);
}
