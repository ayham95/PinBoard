package com.me.test.pinboard.pinboard.Model;

/**
 * Created by ayham on 3/18/16.
 */
public class Note {
    private String title;
    private String description;
    private int color;


    public Note(String title, String description, int color)
    {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
