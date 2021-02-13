package com.teleport.fwoj_backend.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.annMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.ann;
import com.teleport.fwoj_backend.service.annService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class

annServiceImpl implements annService {

    @Autowired
    private annMapper annMapperObject;
    @Autowired
    private userMapper userMapperObject;

    @Override
    public String getAnnList(int page, int pre) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int start = pre * (page - 1);
        int num = pre;
        List<ann> list = annMapperObject.getAnnList(start,num);
        int total = annMapperObject.getAnnSum();
        for(int i = 0 ; i < list.size(); i ++)
        {
            list.get(i).setDate(list.get(i).getDate().substring(0,10));
        }
        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("error", "0");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getAnnDetail(int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        ann annObject = annMapperObject.getAnnDetail(id);

        HashMap s = new HashMap();
        s.put("data", annObject);
        if (annObject != null)
            s.put("error", "0");
        return mapper.writeValueAsString(s);
    }


    @Override
    public String getAnnDetailByIdAdmin(String token,int id) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            ann annObject = annMapperObject.getAnnDetailByIdAdmin(id);
            if(annObject != null)
            {
                s.put("data",annObject);
                s.put("error","0");
            }
            else
                s.put("error","2");
        }
        else
            s.put("error","1");

        return mapper.writeValueAsString(s);
    }

    @Override
    public String deleteAnnById(String token,int id) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 删除失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(annMapperObject.deleteAnnById(id) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getAnnListAndNumAdmin(int page, int pre, String key, String token)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            int start = pre * (page - 1);
            int num = pre;
            List<ann> list = annMapperObject.getAnnListAdmin(start,num,key);
            for(int i = 0 ; i < list.size(); i ++)
            {
                list.get(i).setDate(list.get(i).getDate().substring(0,10));
            }
            s.put("data",list);
            s.put("num",annMapperObject.getAnnSumAdmin());
            s.put("error","0");
        }
        else
            s.put("error","1");
        return  mapper.writeValueAsString(s);

    }



    @Override
    public String changeAnnVisible(String token,int id) throws JsonProcessingException {

        //error
        //0 正常 1 越权 2 删除失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(annMapperObject.getAnnVisibleById(id) == 1)
                if(annMapperObject.setAnnVisibleById(id,false) == 1)
                    s.put("error","0");
                else
                    s.put("error","2");
            else
                if(annMapperObject.setAnnVisibleById(id,true) == 1)
                    s.put("error","0");
                else
                    s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
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
    public String updateAnn(String token,int id,String title,String content) throws JsonProcessingException {

        //error
        //0 正常 1 越权 2 删除失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(annMapperObject.updateAnn(id,title,content) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

}
