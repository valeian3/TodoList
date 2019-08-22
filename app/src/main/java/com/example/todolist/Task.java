package com.example.todolist;

public class Task {

    private String taskId;
    private String Name;

    public Task(){ }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Task(String id, String name){
        Name = name;
        taskId = id;
    }

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
