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


}
