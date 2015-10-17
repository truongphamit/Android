package com.android.truongpq.tet.models;

/**
 * Created by truongpq on 10/7/15.
 */
public class Title {
    private int id;
    private String title;

    public Title() {
    }

    public Title(int id, String title) {
        this.id = id;
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
}
