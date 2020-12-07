package com.teleport.fwoj_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.annMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.ann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class annServiceImpl implements annService{

    @Autowired
    private annMapper annMapperObject;
    @Autowired
    private userMapper userMapperObject;

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
    public ann getAnnDetailByIdAdmin(int id) {
        return annMapperObject.getAnnDetailByIdAdmin(id);
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
    public String createAnn(String title,String content,String token) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 删除失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getTypeByToken(token) != null && userMapperObject.getTypeByToken(token).equals("admin"))
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date =formatter.format(new Date());
            if(annMapperObject.createAnn(date,title,content,userMapperObject.getUserIdByToken(token)) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

    @Override
    public boolean updateAnn(int id, String title, String content) {
        if(annMapperObject.updateAnn(id,title,content) == 1)
            return true;
        else
            return false;
    }

}
