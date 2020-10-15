package com.teleport.fwoj_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class problem {

    private Integer id;
    private String title;
    private String des;
    private String input;
    private String output;
    private String inputExample;
    private String outputExample;
    private String hint;
    private Integer acSubmit;
    private Integer totalSubmit;

    private double acRate;

    public problem(Integer id, String title, String des, String input, String output, String inputExample, String outputExample,String hint, Integer acSubmit, Integer totalSubmit) {
        this.id = id;
        this.title = title;
        this.des = des;
        this.input = input;
        this.output = output;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
        this.hint = hint;
        this.acSubmit = acSubmit;
        this.totalSubmit = totalSubmit;
        this.acRate = (acSubmit * 1.0) / (totalSubmit* 1.0)  ;
    }
}
