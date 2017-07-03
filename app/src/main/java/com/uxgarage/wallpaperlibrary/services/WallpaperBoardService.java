package com.uxgarage.wallpaperlibrary.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.webkit.URLUtil;

import com.bluelinelabs.logansquare.LoganSquare;
import com.uxgarage.amoledblackwallpapers.R;
import com.uxgarage.wallpaperlibrary.items.WallpaperJson;
import com.uxgarage.wallpaperlibrary.receivers.WallpaperBoardReceiver;
import com.uxgarage.wallpaperlibrary.utils.Extras;
import com.uxgarage.wallpaperlibrary.utils.LogUtil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

public class WallpaperBoardService extends IntentService {

    private static final String SERVICE = "com.uxgarage.wallpaperlibrary.services";

    public WallpaperBoardService() {
        super(SERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            String wallpaperUrl = getResources().getString(R.string.wallpaper_json);
            if (!URLUtil.isValidUrl(wallpaperUrl)) return;

            URL url = new URL(wallpaperUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(15000);

            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(WallpaperBoardReceiver.PROCESS_RESPONSE);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                WallpaperJson wallpapersJson = LoganSquare.parse(stream, WallpaperJson.class);
                if (wallpapersJson == null) return;

                int size = wallpapersJson.getWallpapers.size();
                broadcastIntent.putExtra(Extras.EXTRA_PACKAGE_NAME, getPackageName());
                broadcastIntent.putExtra(Extras.EXTRA_SIZE, size);
                sendBroadcast(broadcastIntent);
            }
        } catch (Exception e) {
            LogUtil.e(Log.getStackTraceString(e));
        }
    }
}
