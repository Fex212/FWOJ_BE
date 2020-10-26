package com.teleport.fwoj_backend.service.Index;

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
    public int getContestSum() {
        return contestMapperObject.getContestSum();
    }

    @Override
    public contest getContestDetail(int id) throws ParseException {
        contest ct = contestMapperObject.getContestDetail(id);
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
        return ct;
    }
}
