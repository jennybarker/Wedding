package com.jenny.android.wedding.model;

public class Comment {

    private String comment;
    private String publisher;
    private String commmentid;

    public Comment(String comment, String publisher, String commentid) {
        this.comment = comment;
        this.publisher = publisher;
        this.commmentid = commentid;
    }

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCommmentid() {
        return commmentid;
    }

    public void setCommmentid(String commmentid) {
        this.commmentid = commmentid;
    }
}
