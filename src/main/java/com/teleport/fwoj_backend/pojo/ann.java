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
    private Integer author_id;
    private String author_username;

}
