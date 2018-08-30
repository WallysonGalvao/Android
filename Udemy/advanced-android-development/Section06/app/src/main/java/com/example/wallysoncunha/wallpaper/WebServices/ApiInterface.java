package com.example.wallysoncunha.wallpaper.WebServices;

import com.example.wallysoncunha.wallpaper.Models.Collection;
import com.example.wallysoncunha.wallpaper.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Wallyson Galvão on 28/08/2018.
 */
public interface ApiInterface {

    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") String id);

    @GET("collections/featured")
    Call<List<Collection>> getCollections();

    @GET("collections/{id}")
    Call<Collection> getInformationOfCollection(@Path("id") int id);

    @GET("collections/{id}/photos")
    Call<List<Photo>> getPhotosOfCollection(@Path("id") int id);
}
