package com.blog.blog.models;

public class Tag {
    private int tagID;
    private String name;

    public Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
