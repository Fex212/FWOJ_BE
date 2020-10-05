package com.teleport.service;

import com.teleport.dao.annMapper;
import com.teleport.dao.problemMapper;
import com.teleport.pojo.ann;
import java.util.List;

public class annServiceImpl implements annService{
    //调用dao层，设置annObject为set接口，方便spring管理
    private annMapper annMapperObject;
    public void setAnnMapperObject(annMapper annMapperObject) {
        this.annMapperObject = annMapperObject;
    }

    //获得公告列表
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
