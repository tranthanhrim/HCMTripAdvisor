package com.example.tranthanhrim1995.hcmtripadvisor.Model;

/**
 * Created by tranthanhrim1995 on 1/12/2017.
 */

public class Comment {
    private String avatar;
    private String comment;
    private String date;
    private String time;
    private String username;

    public Comment(String username, String avatar, String comment, String date, String time) {
        this.avatar = avatar;
        this.comment = comment;
        this.date = date;
        this.time = time;
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
