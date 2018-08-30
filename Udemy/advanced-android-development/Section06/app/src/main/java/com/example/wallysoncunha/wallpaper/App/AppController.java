package com.example.wallysoncunha.wallpaper.App;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Wallyson Galvão on 30/08/2018.
 */

public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("wallpaper.realm")
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}