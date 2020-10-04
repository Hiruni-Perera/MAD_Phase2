package com.example.easyapp;

public class ToDoModel {
    private int id;
    private String task ,description;
    private long started,finished;

    public ToDoModel(){

    }



    public ToDoModel(String task,String description, long started, long finished) {
        this.task  = task ;
        this.description = description;
        this.started = started;
        this.finished = finished;
    }

    public ToDoModel(int id, String task, String description, long started, long finished) {
        this.id = id;
        this.task = task;
        this.description = description;
        this.started = started;
        this.finished = finished;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id  = id ;
    }

    public String getTask() {
        return task ;
    }

    public void setTask(String task) {
        this.task  = task ;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }


}


