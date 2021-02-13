package com.teleport.fwoj_backend.service.Impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teleport.fwoj_backend.mapper.rankMapper;
import com.teleport.fwoj_backend.mapper.userMapper;
import com.teleport.fwoj_backend.pojo.rank;
import com.teleport.fwoj_backend.service.rankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class rankServiceImpl implements rankService {

    @Autowired
    rankMapper rankMapperObject;
    @Autowired
    userMapper userMapperObject;

    @Override
    public String getRank(int page, int pre) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int start = pre * (page - 1);
        int num = pre;
        int total = userMapperObject.getUserNum();
        List<rank> list = rankMapperObject.getRankList(start,num);

        int len = list.size();
        for(int i = 0 ; i < len ; i ++)
            list.get(i).setRank(i+1+pre*(page-1));

        HashMap s = new HashMap();
        s.put("data", list);
        s.put("total", total);
        s.put("error", "0");
        return mapper.writeValueAsString(s);
    }

    @Override
    public String getRankChartData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap s = new HashMap();
        s.put("usernameList", rankMapperObject.getTop10Username());
        s.put("solvedNumList", rankMapperObject.getTop10SolvedNum());
        s.put("error", "0");
        return mapper.writeValueAsString(s);
    }
}
