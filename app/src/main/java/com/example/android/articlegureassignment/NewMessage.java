package com.example.android.articlegureassignment;

import android.widget.Button;

public class NewMessage {
    private String photoUrl;
    private String description;
    private int likes;
    public Button likeButton;

    public NewMessage() {
    }

    public NewMessage(String photoUrl, String description, int likes) {
        this.photoUrl = photoUrl;
        this.description = description;
        this.likes = likes;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
