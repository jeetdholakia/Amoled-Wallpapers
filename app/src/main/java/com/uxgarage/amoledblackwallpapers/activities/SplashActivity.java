package com.uxgarage.amoledblackwallpapers.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.uxgarage.wallpaperlibrary.activities.WallpaperBoardSplashActivity;


public class SplashActivity extends WallpaperBoardSplashActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSplashActivity(savedInstanceState, MainActivity.class);
    }
}
