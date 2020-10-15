package com.teleport.fwoj_backend.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class state {
    int id;
    int problemId;
    int authorId;
    String date;
    String language;
    String state;
    String memoryCost;
    String timeCost;
    String code;

    public state(int id,int problemId,int authorId,String date,String language,String state,String memoryCost,String timeCost)
    {
        this.id = id;
        this.problemId = problemId;
        this.authorId = authorId;
        this.date = date;
        this.language = language;
        this.state = state;
        this.memoryCost = memoryCost;
        this.timeCost = timeCost;
    }
}
