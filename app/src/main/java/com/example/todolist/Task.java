package com.example.todolist;

public class Task {

    private String Name;

    public Task(){ }

    public Task(String name){
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
