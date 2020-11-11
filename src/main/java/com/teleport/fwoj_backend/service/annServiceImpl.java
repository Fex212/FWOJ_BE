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

    @Override
    public int getAnnSum() {
        int total = annMapperObject.getAnnSum();
        return total;
    }

    @Override
    public ann getAnnDetail(int id) {
        return annMapperObject.getAnnDetail(id);
    }

    @Override
    public List<ann> getAnnListAdmin(int page, int pre,String key) {
        int start = pre * (page - 1);
        int num = pre;
        List<ann> list = annMapperObject.getAnnListAdmin(start,num,key);
        for(int i = 0 ; i < list.size(); i ++)
        {
            list.get(i).setDate(list.get(i).getDate().substring(0,10));
        }
        return list;
    }

    @Override
    public int getAnnSumAdmin() {
        int total = annMapperObject.getAnnSumAdmin();
        return total;
    }

    @Override
    public boolean deleteAnnById(int id) {
        if(annMapperObject.deleteAnnById(id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean changeAnnVisible(int id) {
        if(annMapperObject.getAnnVisibleById(id) == 1)
            if(annMapperObject.setAnnVisibleById(id,false) == 1)
                return true;
            else
                return false;
        else
            if(annMapperObject.setAnnVisibleById(id,true) == 1)
                return true;
            else
                return false;
    }

    @Override
    public boolean createAnn(String date, String title, String content, int authorId) {
        if(annMapperObject.createAnn(date,title,content,authorId) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateAnn(int id, String title, String content) {
        if(annMapperObject.updateAnn(id,title,content) == 1)
            return true;
        else
            return false;
    }

}
