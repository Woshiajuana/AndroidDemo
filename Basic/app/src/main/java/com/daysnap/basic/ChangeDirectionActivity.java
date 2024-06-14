package com.daysnap.basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class ChangeDirectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_direction);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 在配置项变更时触发，比如屏幕方向发生变更等等

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 竖屏
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 横屏
        }
    }
}