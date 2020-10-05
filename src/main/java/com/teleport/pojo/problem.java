package com.teleport.pojo;

public class problem {

    private int id;
    private String title;
    private String des;
    private String input;
    private String output;
    private String inputExample;
    private String outputExample;
    private String hint;
    private int acSubmit;
    private int totalSubmit;

    private double acRate;

    public problem(int id, String title, String des, String input, String output, String inputExample, String outputExample,String hint, int acSubmit, int totalSubmit) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInputExample() {
        return inputExample;
    }

    public void setInputExample(String inputExample) {
        this.inputExample = inputExample;
    }

    public String getOutputExample() {
        return outputExample;
    }

    public void setOutputExample(String outputExample) {
        this.outputExample = outputExample;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getAcSubmit() {
        return acSubmit;
    }

    public void setAcSubmit(int acSubmit) {
        this.acSubmit = acSubmit;
    }

    public int getTotalSubmit() {
        return totalSubmit;
    }

    public void setTotalSubmit(int totalSubmit) {
        this.totalSubmit = totalSubmit;
    }

    public double getAcRate() {
        return acRate;
    }

    public void setAcRate(double acRate) {
        this.acRate = acRate;
    }




}
