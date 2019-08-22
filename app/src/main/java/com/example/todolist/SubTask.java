package com.example.todolist;

public class SubTask {

    private String SubName;
    private String subTaskId;

    public SubTask(){ }

    public SubTask(String id,String sName){
        SubName = sName;
        subTaskId = id;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getName() {
        return SubName;
    }

    public void setName(String sName) {
        this.SubName = sName;
    }
}

