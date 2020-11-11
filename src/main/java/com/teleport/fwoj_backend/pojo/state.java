package com.teleport.fwoj_backend.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class state {
    int id;
    int problemId;
    int authorId;
    String authorName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date date;
    String language;
    String state;
    String memoryCost;
    String timeCost;
    String code;

    //状态列表对象
    public state(int id, int problemId, String authorName, Date date, String language,
                 String state, String memoryCost, String timeCost) {
        this.id = id;
        this.problemId = problemId;
        this.authorName = authorName;
        this.date = date;
        this.language = language;
        this.state = state;
        this.memoryCost = memoryCost;
        this.timeCost = timeCost;
    }

    //状态详情对象
    public state(int id, int problemId, String authorName, Date date, String language,
                 String state, String memoryCost, String timeCost,String code) {
        this.id = id;
        this.problemId = problemId;
        this.authorName = authorName;
        this.date = date;
        this.language = language;
        this.state = state;
        this.memoryCost = memoryCost;
        this.timeCost = timeCost;
        this.code = code;
    }

}
