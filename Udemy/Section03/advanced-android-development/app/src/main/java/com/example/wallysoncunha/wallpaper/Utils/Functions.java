package com.example.wallysoncunha.wallpaper.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.wallysoncunha.wallpaper.R;

/**
 * Created by Wallyson Galvão on 28/08/2018.
 */

public class Functions {

    public static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment) {
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }
}