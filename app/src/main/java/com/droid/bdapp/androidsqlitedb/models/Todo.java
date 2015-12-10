package com.droid.bdapp.androidsqlitedb.models;

/**
 * Created by mdruhulamin on 12/10/15.
 */
public class Todo {

    private int id;
    private String title;
    private String note;

    public Todo() {
    }

    public Todo(String note, String title) {
        this.note = note;
        this.title = title;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
