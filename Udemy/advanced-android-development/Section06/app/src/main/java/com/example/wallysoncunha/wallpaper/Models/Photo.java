package com.example.wallysoncunha.wallpaper.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Wallyson Galvão on 29/08/2018.
 */

public class Photo extends RealmObject {

    @SerializedName("id")
    @PrimaryKey
    private String id;

    @SerializedName("description")
    private String description;

    @SerializedName("urls")
    private PhotoUrl url = new PhotoUrl();

    @SerializedName("user")
    private User user = new User();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoUrl getUrl() {
        return url;
    }

    public void setUrl(PhotoUrl url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}