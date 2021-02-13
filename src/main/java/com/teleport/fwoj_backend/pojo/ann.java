package com.teleport.fwoj_backend.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ann {

    private Integer id;
    private String date;
    private String title;
    private String content;
    private int authorId;
    private String authorName;
    private boolean visible;

}
