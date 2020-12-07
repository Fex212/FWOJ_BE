package com.teleport.fwoj_backend.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.contestMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class contestServiceImpl implements contestService{
    @Autowired
    private contestMapper contestMapperObject;
    @Autowired
    private userMapper userMapperObject;
    @Override
    public String getContestList(int page, int pre) throws ParseException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int start = pre * (page - 1);
        int num = pre;
        List<contest> list = contestMapperObject.getContestList(start,num);
        int len = list.size();
        for(int i = 0 ; i < len ; i ++)
        {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date currentTime = new Date();
            Date startTime = ft.parse(list.get(i).getStartTime());
            Date endTime = ft.parse(list.get(i).getEndTime());
            if(startTime.getTime() > currentTime.getTime())
                list.get(i).setState("筹备中");
            if(startTime.getTime() <= currentTime.getTime() && endTime.getTime() > currentTime.getTime())
                list.get(i).setState("进行中");
            if(endTime.getTime() <= currentTime.getTime())
                list.get(i).setState("已结束");
        }
        int total = contestMapperObject.getContestSum();
        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("status", 200);
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getContestListAdmin(String token,int page, int pre,String key) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            int start = pre * (page - 1);
            int num = pre;
            s.put("data",contestMapperObject.getContestListAdmin(start,num,key));
            s.put("num",contestMapperObject.getContestSumAdmin());
            s.put("error",0);
        }
        else
            s.put("error",1);
        return  mapper.writeValueAsString(s);
    }

    @Override
    public String getContestDetail(int id) throws ParseException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        contest ct = contestMapperObject.getContestDetail(id);
        if(ct != null)
        {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date currentTime = new Date();
            Date startTime = ft.parse(ct.getStartTime());
            Date endTime = ft.parse(ct.getEndTime());
            if(startTime.getTime() > currentTime.getTime())
                ct.setState("筹备中");
            if(startTime.getTime() <= currentTime.getTime() && endTime.getTime() > currentTime.getTime())
                ct.setState("进行中");
            if(endTime.getTime() <= currentTime.getTime())
                ct.setState("已结束");
        }
        s.put("data", ct);
        if (ct != null)
            s.put("error", "0");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getContestDetailByIdAdmin(String token,int id) throws JsonProcessingException {

        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if (userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin")) {
            contest contestObject = contestMapperObject.getContestDetailByIdAdmin(id);
            if (contestObject != null) {
                s.put("data", contestObject);
                s.put("error", "0");
            } else
                s.put("error", "2");
        } else
            s.put("error", "1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String contestVisibleChanged(String token,int id) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if (contestMapperObject.getContestVisibleById(id) == 1)
            {
                if(contestMapperObject.setContestVisibleById(id, false) == 1)
                    s.put("error","0");
                else
                    s.put("error","2");
            }
            else
                if(contestMapperObject.setContestVisibleById(id, true) == 1)
                    s.put("error","0");
                else
                    s.put("error","2");
        }
        else
            s.put("error","1");
        return  mapper.writeValueAsString(s);
    }

    @Override
    public String deleteContestById(String token,int id) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 删除失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(contestMapperObject.deleteContestById(id) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String createContest(String token,String title, String des, String problemList, String startTime, String endTime, boolean visible, String authorName) throws JsonProcessingException {
        //error
        //0 正常 1 越权 2 失败
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(contestMapperObject.createContest(title,des,problemList,startTime,endTime,false,userMapperObject.getUserNameByToken(token)) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String editContestById(String token,String title, String des, String problemList, String startTime, String endTime,int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        if(userMapperObject.getUserTypeByToken(token) != null &&  userMapperObject.getUserTypeByToken(token).equals("admin"))
        {
            if(contestMapperObject.editContestById(title,des,problemList,startTime,endTime,id) == 1)
                s.put("error","0");
            else
                s.put("error","2");
        }
        else
            s.put("error","1");
        return mapper.writeValueAsString(s);
    }

}
