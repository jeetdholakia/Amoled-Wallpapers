package com.uxgarage.wallpaperlibrary.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.danimahardhika.android.helpers.core.FileHelper;
import com.google.android.apps.muzei.api.Artwork;
import com.google.android.apps.muzei.api.RemoteMuzeiArtSource;
import com.uxgarage.wallpaperlibrary.helpers.MuzeiHelper;
import com.uxgarage.wallpaperlibrary.helpers.WallpaperHelper;
import com.uxgarage.wallpaperlibrary.items.Wallpaper;
import com.uxgarage.wallpaperlibrary.preferences.Preferences;

import java.io.File;

/*
 * Amoled Wallpapers
 *
 * Copyright (c) 2017 Jeet Dholakia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public abstract class WallpaperBoardMuzeiService extends RemoteMuzeiArtSource {

    private MuzeiHelper mMuzeiHelper;

    public WallpaperBoardMuzeiService(String name) {
        super(name);
    }

    public void startCommand(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            boolean restart = intent.getBooleanExtra("restart", false);
            if (restart) {
                try {
                    onTryUpdate(UPDATE_REASON_USER_NEXT);
                } catch (RetryException ignored) {
                }
            }
        }
    }

    public void initMuzeiService() {
        super.onCreate();
        mMuzeiHelper = new MuzeiHelper(this);
        setUserCommands(BUILTIN_COMMAND_ID_NEXT_ARTWORK);
    }

    public void tryUpdate(String wallpaperUrl) {
        try {
            if (Preferences.get(this).isConnectedAsPreferred()) {
                Wallpaper wallpaper = mMuzeiHelper.getRandomWallpaper(wallpaperUrl);
                if (wallpaper != null) publishArtwork(wallpaper);
            }
        } catch (Exception ignored) {
        }
    }

    private void publishArtwork(Wallpaper wallpaper) {
        File file = new File(Preferences.get(this).getWallsDirectory()
                + wallpaper.getName() + WallpaperHelper.IMAGE_EXTENSION);
        Uri uri = null;
        if (file.exists()) uri = FileHelper.getUriFromFile(this, getPackageName(), file);
        if (uri == null) uri = Uri.parse(wallpaper.getUrl());

        publishArtwork(new Artwork.Builder()
                .title(wallpaper.getName())
                .byline(wallpaper.getAuthor())
                .imageUri(uri)
                .build());

        scheduleUpdate(System.currentTimeMillis() +
                Preferences.get(this).getRotateTime());
    }
}
