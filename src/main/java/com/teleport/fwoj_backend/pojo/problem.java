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
    private String authorName;
    private String createTime;
    private int acSubmit;
    private int totalSubmit;
    private boolean visible;
    private double acRate;

    //创建问题对象
    public problem(String title, String des, String input, String output, String inputExample, String outputExample, String hint, String authorName, String createTime, int acSubmit, int totalSubmit, boolean visible) {
        this.title = title;
        this.des = des;
        this.input = input;
        this.output = output;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
        this.hint = hint;
        this.authorName = authorName;
        this.createTime = createTime;
        this.acSubmit = acSubmit;
        this.totalSubmit = totalSubmit;
        this.visible = visible;
    }
    //编辑问题对象
    public problem(int id,String title, String des, String input, String output, String inputExample, String outputExample, String hint) {
        this.id = id;
        this.title = title;
        this.des = des;
        this.input = input;
        this.output = output;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
        this.hint = hint;
    }

    //问题详情对象
    public problem(Integer id, String title, String des, String input, String output, String inputExample, String outputExample, String hint, Integer acSubmit, Integer totalSubmit) {
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

    //问题列表（用户） id title acSubmit totalSubmit
    public problem(int id,String title,int acSubmit,int totalSubmit)
    {
        this.id = id;
        this.title = title;
        this.acSubmit = acSubmit;
        this.totalSubmit = totalSubmit;
    }


    //获取问题列表(Admin) id title createTime visible authorName
    public problem(int id,String title,String createTime,boolean visible,String authorName)
    {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.authorName = authorName;
        this.visible = visible;
    }

//    根据id设置visible
    public problem(int id,boolean visible)
    {
        this.id = id;
        this.visible = visible;
    }


}
