package com.uxgarage.amoledblackwallpapers.services;

import android.content.Intent;

import com.google.android.apps.muzei.api.RemoteMuzeiArtSource;
import com.uxgarage.amoledblackwallpapers.R;
import com.uxgarage.wallpaperlibrary.services.WallpaperBoardMuzeiService;

public class MuzeiService extends WallpaperBoardMuzeiService {

    private static final String SOURCE_NAME = "WallpaperBoard:MuzeiArtSource";

    public MuzeiService() {
        super(SOURCE_NAME);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startCommand(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        initMuzeiService();
    }

    @Override
    protected void onTryUpdate(int reason) throws RemoteMuzeiArtSource.RetryException {
        String wallpaperUrl = getResources().getString(R.string.wallpaper_json);
        tryUpdate(wallpaperUrl);
    }
}
