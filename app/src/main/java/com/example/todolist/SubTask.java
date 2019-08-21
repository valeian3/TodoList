package com.example.todolist;

public class SubTask {

    private String SubName;

    public SubTask(){ }

    public SubTask(String name){
        SubName = name;
    }

    public String getName() {
        return SubName;
    }

    public void setName(String name) {
        this.SubName = name;
    }
}

