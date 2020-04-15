package com.jenny.android.wedding.model;

public class Story {

    private String storyid;
    private String storyimage;
    private String description;
    private String title;

    public Story(String storyid, String storyimage, String description, String title) {
        this.storyid = storyid;
        this.storyimage = storyimage;
        this.description = description;
        this.title = title;
    }

    public Story() {
    }

    public String getStoryid() {
        return storyid;
    }

    public String getStoryimage() {
        return storyimage;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
