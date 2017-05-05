package com.untaek.oneroom.format;

import java.io.Serializable;

/**
 * Created by ejdej on 2017-05-04.
 */

public class ListTitleFormat implements Serializable {

    public static String TITLE = "title";
    public static String AUTHOR = "author";
    public static String DATE = "date";
    public static String BUNDLE = "bundle";

    private String title;
    private String author;
    private String date;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
