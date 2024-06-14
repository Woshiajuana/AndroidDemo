package com.daysnap.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;

public class ReturnDesktopActivity extends AppCompatActivity {

    private DesktopReceiver desktopReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_desktop);

        desktopReceiver = new DesktopReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(desktopReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(desktopReceiver);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if (isInPictureInPictureMode) {
            Log.d("ds => ", "进入画中画模式");
        } else {
            Log.d("ds => ", "退出了画中画模式");
        }
    }

    // 定义一个返回到桌面的广播接收器
    private class DesktopReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra("reason");
                if ("homekey".equals(reason) || "recentapps".equals(reason)) {
                    // 提供画中画模式 8.0 之后才有
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !isInPictureInPictureMode()) {
                        // 创建画中画模式的参数构建器
                        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
                        Rational rational = new Rational(10, 5);
                        builder.setAspectRatio(rational);
                        enterPictureInPictureMode(builder.build());
                    }
                }
            }
        }
    }
}