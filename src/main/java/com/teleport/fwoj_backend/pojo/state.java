package com.teleport.fwoj_backend.pojo;
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
    Date date;
    String language;
    String state;
    String memoryCost;
    String timeCost;
    String code;

    public state(int problemId,int authorId,Date date,String language,String state,String memoryCost,String timeCost,String code)
    {
        this.problemId = problemId;
        this.authorId = authorId;
        this.date = date;
        this.language = language;
        this.state = state;
        this.memoryCost = memoryCost;
        this.timeCost = timeCost;
        this.code = code;
    }
}
