package com.ohshell.headline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ohshell.headline.utils.OhShellThemeUtil
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 设置沉浸式状态栏
        QMUIStatusBarHelper.translucent(this)

        if (OhShellThemeUtil.isDark(this)) {
            // 状态栏文字颜色
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        } else {
            // 状态栏文字颜色
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }
    }
}