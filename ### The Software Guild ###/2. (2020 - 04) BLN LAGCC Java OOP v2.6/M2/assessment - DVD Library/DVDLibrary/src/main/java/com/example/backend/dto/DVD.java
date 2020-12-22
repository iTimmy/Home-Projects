package com.example.backend.dto;

import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;

public class DVD {
    private String title;
    private String releaseDate;
    private String directorName;
    private String rating;
    private String notes;

    public DVD(String title, String releaseDate, String directorName, String rating, String notes) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        this.rating = rating;
        this.notes = notes;
    }

    public DVD() {
    }

    public DVD(String title) {
        this.title = title;
    }

    // ##### ---------- TITLE ---------- ##### //
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // ##### ---------- RELEASE DATE ---------- ##### //
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    // ##### ---------- DIRECTOR NAME ---------- ##### //
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    // ##### ---------- RATING ---------- ##### //
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    // ##### ---------- NOTES ---------- ##### //
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}