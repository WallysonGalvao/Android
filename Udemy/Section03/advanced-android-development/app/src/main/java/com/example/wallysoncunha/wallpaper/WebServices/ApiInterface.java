package com.example.wallysoncunha.wallpaper.WebServices;

import com.example.wallysoncunha.wallpaper.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Wallyson Galvão on 28/08/2018.
 */
public interface ApiInterface {

    @GET("photos")
    Call<List<Photo>> getPhotos();
}
