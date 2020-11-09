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
    boolean visible;
    String authorName;

    //设置visible对象
    public contest(int id, boolean visible) {
        this.id = id;
        this.visible = visible;
    }

    //contestAdmin列表
    public contest(int id, String title, String startTime, String endTime, boolean visible,String authorName) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.visible = visible;
        this.authorName = authorName;
    }

    //contestList列表
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
    //contestDetail(user)
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
