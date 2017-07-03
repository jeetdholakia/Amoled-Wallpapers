package com.uxgarage.amoledblackwallpapers.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.uxgarage.amoledblackwallpapers.services.MuzeiService;
import com.uxgarage.wallpaperlibrary.activities.WallpaperBoardMuzeiActivity;

public class MuzeiActivity extends WallpaperBoardMuzeiActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initMuzeiActivity(savedInstanceState, MuzeiService.class);
    }
}
