package com.ed.model;

import java.sql.Timestamp;

public class Document {
    private int id;
    private String title;
    private String description;
    private String filePath;
    private int uploaderId;
    private Timestamp uploadedAt;

    // Constructors
    public Document() {}

    public Document(String title, String description, String filePath, int uploaderId) {
        this.title = title;
        this.description = description;
        this.filePath = filePath;
        this.uploaderId = uploaderId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(int uploaderId) {
        this.uploaderId = uploaderId;
    }

    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}
