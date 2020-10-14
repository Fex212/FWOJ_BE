package com.teleport.fwoj_backend.pojo;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class contest {
    int id;
    String title;
    String des;
    String problemList;
    String startTime;
    String endTime;
    String state;
    //getContestList
    public contest(int id,String title,String startTime,String endTime)
    {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = "";
        this.des = "";
        this.problemList = "";
    }
    public contest(int id,String title,String des,String problemList,String startTime,String endTime)
    {
        this.id = id;
        this.title = title;
        this.des = des;
        this.problemList = problemList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = "";
    }
}
