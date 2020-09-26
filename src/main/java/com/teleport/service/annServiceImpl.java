package com.teleport.service;

import com.teleport.dao.annMapper;
import com.teleport.pojo.ann;
import java.util.List;

public class annServiceImpl implements annService{
    //调用dao层，设置annObject为set接口，方便spring管理
    private annMapper annMapperObject;
    public void setAnnMapperObject(annMapper annMapperObject) {
        this.annMapperObject = annMapperObject;
    }

    public List<ann> getAnnList(int page,int preNum)
    {
        return annMapperObject.getAnnList(page,preNum);
    }

}
