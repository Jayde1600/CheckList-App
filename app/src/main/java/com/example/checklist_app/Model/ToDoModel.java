package com.example.checklist_app.Model;

public class ToDoModel extends TaskID {

    private String task, due;
    private int status;

    public String getTask() {
        return task;
    }

    public String getDue() {
        return due;
    }

    public int getStatus() {
        return status;
    }
}
