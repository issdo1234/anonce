package com.example.chatapp1;


import com.google.firebase.ktx.Firebase;

public class DataModel  {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    DataModel(){}

    public DataModel(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}


