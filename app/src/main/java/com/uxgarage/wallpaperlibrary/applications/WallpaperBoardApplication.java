package com.uxgarage.wallpaperlibrary.applications;

import android.app.Application;
import android.content.Intent;
import android.util.Patterns;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.uxgarage.amoledblackwallpapers.R;
import com.uxgarage.wallpaperlibrary.activities.WallpaperBoardCrashReport;
import com.uxgarage.wallpaperlibrary.helpers.LocaleHelper;
import com.uxgarage.wallpaperlibrary.preferences.Preferences;
import com.uxgarage.wallpaperlibrary.utils.ImageConfig;
import com.uxgarage.wallpaperlibrary.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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

public class WallpaperBoardApplication extends Application {

    private Thread.UncaughtExceptionHandler mHandler;

    public void initApplication() {
        super.onCreate();
        if (!ImageLoader.getInstance().isInited())
            ImageLoader.getInstance().init(ImageConfig.getImageLoaderConfiguration(this));

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Font-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        //Enable logging
        LogUtil.setLoggingEnabled(true);

        if (Patterns.EMAIL_ADDRESS.matcher(getResources().getString(R.string.dev_email)).matches()) {
            mHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this::handleUncaughtException);
        }

        if (Preferences.get(this).isTimeToSetLanguagePreference()) {
            Preferences.get(this).setLanguagePreference();
            return;
        }

        LocaleHelper.setLocale(this);
    }

    private void handleUncaughtException(Thread thread, Throwable throwable) {
        try {
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String dateTime = dateFormat.format(new Date());
            sb.append("Crash Time : ").append(dateTime).append("\n");
            sb.append("Class Name : ").append(throwable.getClass().getName()).append("\n");
            sb.append("Caused By : ").append(throwable.toString()).append("\n");

            for (StackTraceElement element : throwable.getStackTrace()) {
                sb.append("\n");
                sb.append(element.toString());
            }

            Intent intent = new Intent(this, WallpaperBoardCrashReport.class);
            intent.putExtra(WallpaperBoardCrashReport.EXTRA_STACKTRACE, sb.toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(intent);
        } catch (Exception e) {
            if (mHandler != null) {
                mHandler.uncaughtException(thread, throwable);
                return;
            }
        }
        System.exit(1);
    }
}
