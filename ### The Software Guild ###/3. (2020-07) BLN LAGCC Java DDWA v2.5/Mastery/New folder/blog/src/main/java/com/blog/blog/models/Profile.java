package com.blog.blog.models;

public class Profile {
    private int profileID;
    private String icon;
    private String cover;
    private String wallpaper;

    public Profile(int profileID, String icon, String cover, String wallpaper) {
        this.profileID = profileID;
        this.icon = icon;
        this.cover = cover;
        this.wallpaper = wallpaper;
    }

    public Profile(String icon, String cover, String wallpaper) {
        this.icon = icon;
        this.cover = cover;
        this.wallpaper = wallpaper;
    }

    public Profile(String icon) {
        this.icon = icon;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }

}
