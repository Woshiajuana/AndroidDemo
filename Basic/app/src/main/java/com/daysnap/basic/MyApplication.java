package com.daysnap.basic;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

public class MyApplication extends Application {

    private static MyApplication mApp;

    public static MyApplication getInstance() {
        return mApp;
    }

    // app 启动的时候就会调用
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 在配置改变的时候调用 例如从竖屏变成横屏
    }
}
