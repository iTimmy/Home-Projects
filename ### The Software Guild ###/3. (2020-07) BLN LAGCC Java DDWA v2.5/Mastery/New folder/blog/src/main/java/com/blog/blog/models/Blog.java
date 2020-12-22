package com.blog.blog.models;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotBlank;

public class Blog {
    private int blogID;
    private int userID;
    @NotBlank(message = "Title cannot be blank.")
    // @UniqueTitle(message = "Please input a unique blog title.")
    private String title;
    private LocalDate date;
    private String content;
    private List<Tag> tags;
    private boolean approved;
    private LocalDate postDate;
    private LocalDate expirationDate;
    private String photo;

    public Blog() {

    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    
    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
