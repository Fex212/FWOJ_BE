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

}
