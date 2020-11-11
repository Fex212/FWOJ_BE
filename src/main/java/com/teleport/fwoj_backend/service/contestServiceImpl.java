package com.teleport.fwoj_backend.service;

import com.teleport.fwoj_backend.mapper.contestMapper;
import com.teleport.fwoj_backend.pojo.contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class contestServiceImpl implements contestService{
    @Autowired
    private contestMapper contestMapperObject;
    @Override
    public List<contest> getContestList(int page, int pre) throws ParseException {
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
        return list;
    }

    @Override
    public List<contest> getContestListAdmin(int page, int pre,String key) {
        int start = pre * (page - 1);
        int num = pre;
        return contestMapperObject.getContestListAdmin(start,num,key);
    }

    @Override
    public int getContestSum() {
        return contestMapperObject.getContestSum();
    }

    @Override
    public int getContestSumAdmin() {
        return contestMapperObject.getContestSumAdmin();
    }

    @Override
    public contest getContestDetail(int id) throws ParseException {
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
        return ct;
    }

    @Override
    public boolean contestVisibleChanged(int id) {
        if (contestMapperObject.getContestVisibleById(id) == 1)
        {
            if(contestMapperObject.setContestVisibleById(id, false) == 1)
                return true;
            else
                return false;
        }
        else
            if(contestMapperObject.setContestVisibleById(id, true) == 1)
                return true;
            else
                return false;
    }

    @Override
    public boolean deleteContestById(int id) {
        if(contestMapperObject.deleteContestById(id) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean createContest(String title, String des, String problemList, String startTime, String endTime, boolean visible, String authorName) {
        if(contestMapperObject.createContest(title,des,problemList,startTime,endTime,visible,authorName) == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean editContestById(String title, String des, String problemList, String startTime, String endTime,int id) {
        if(contestMapperObject.editContestById(title,des,problemList,startTime,endTime,id) == 1)
            return true;
        else
            return false;
    }

}
