package com.untaek.oneroom.rest;

/**
 * Created by ejdej on 2017-05-15.
 */

public interface BoardService {
    class Post{
        int id;
        int user;
        String title;
        String text;
        String post_date;
    }
    class Comment{
        int id;
        int post;
        int user;
        String text;
        String post_date;
    }

}
