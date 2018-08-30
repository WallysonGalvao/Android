package com.example.wallysoncunha.wallpaper.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Wallyson Galvão on 29/08/2018.
 */

public class ProfileImage extends RealmObject {

    @SerializedName("small")
    private String small;

    @SerializedName("medium")
    private String medium;

    @SerializedName("large")
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}