package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.contestListMapper;
import com.teleport.fwoj_backend.mapper.problemMapper;
import com.teleport.fwoj_backend.pojo.contestList;
import com.teleport.fwoj_backend.pojo.problem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class contestListServiceImpl implements contestListService {

    @Autowired
    private contestListMapper contestListMapperObject;


    @Override
    public List<contestList> getContestList(Integer start, Integer num) {
        return null;
    }

    @Override
    public Integer getContestSum() {
        return null;
    }
}
