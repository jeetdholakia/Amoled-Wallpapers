package com.uxgarage.wallpaperlibrary.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.uxgarage.wallpaperlibrary.utils.LogUtil;
import com.uxgarage.wallpaperlibrary.utils.listeners.WallpaperBoardListener;

public class WallpaperBoardReceiver extends BroadcastReceiver {

    public static final String PROCESS_RESPONSE = "com.uxgarage.wallpaperlibrary.receivers.broadcast.receiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            WallpaperBoardListener listener = (WallpaperBoardListener) context;
            listener.onWallpapersChecked(intent);
        } catch (Exception e) {
            LogUtil.e(Log.getStackTraceString(e));
        }
    }
}
