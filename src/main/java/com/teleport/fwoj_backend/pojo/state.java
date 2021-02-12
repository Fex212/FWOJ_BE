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
    Integer id;
    Integer problemId;
    Integer authorId;
    String authorName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    Date date;
    String language;
    String state;
    String compileInfo;
    int memoryCost;
    int timeCost;
    String code;
//    String problemTitle;
}
