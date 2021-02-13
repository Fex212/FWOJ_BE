package com.teleport.fwoj_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class problem {

    private int id;
    private String title;
    private String des;
    private String input;
    private String output;
    private String inputExample;
    private String outputExample;
    private String hint;
    private int authorId;
    private String authorName;
    private String createTime;
    private int acSubmit;
    private int totalSubmit;
    private boolean visible;
    private double acRate;

    private int isAccept;
    private int isAttempt;


}
