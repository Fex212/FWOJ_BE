package com.teleport.fwoj_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class contestList {
    String title;
    Date start_time;
    Date end_time;
}
