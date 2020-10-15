package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.annMapper;
import com.teleport.fwoj_backend.pojo.ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class annServiceImpl implements annService{

    @Autowired
    private annMapper annMapperObject;

    //获得公告列表
    @Override
    public List<ann> getAnnList(int page,int pre)
    {
        int start = pre * (page - 1);
        int num = pre;
        List<ann> list = annMapperObject.getAnnList(start,num);
        for(int i = 0 ; i < list.size(); i ++)
        {
            list.get(i).setDate(list.get(i).getDate().substring(0,10));
        }
        return list;
    }

    //获得公告条数
    @Override
    public int getAnnSum() {
        int total = annMapperObject.getAnnSum();
        return total;
    }

    //按id查找公告详情
    @Override
    public ann getAnnDetail(int id) {
        return annMapperObject.getAnnDetail(id);
    }

}
