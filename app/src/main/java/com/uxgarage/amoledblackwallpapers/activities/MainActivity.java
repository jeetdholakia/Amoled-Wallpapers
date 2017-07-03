package com.uxgarage.amoledblackwallpapers.activities;

import android.os.Bundle;

import com.uxgarage.amoledblackwallpapers.licenses.License;
import com.uxgarage.wallpaperlibrary.activities.WallpaperBoardActivity;

public class MainActivity extends WallpaperBoardActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initMainActivity(savedInstanceState,
                License.isLicenseCheckerEnabled(),
                License.getRandomString(),
                License.getLicenseKey(),
                License.getDonationProductsId());
    }
}
