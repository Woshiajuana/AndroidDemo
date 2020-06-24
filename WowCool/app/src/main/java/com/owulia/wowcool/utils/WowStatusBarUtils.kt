package com.owulia.wowcool.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

class WowStatusBarUtils private constructor(private val activity: Activity) {

    companion object {
        val with: (Activity) -> WowStatusBarUtils = { WowStatusBarUtils(it) }
    }

    /**
     * 设置全屏，设置状态栏透明
     * */
    fun setScreenFull () {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
            val window = activity.window
            val decorView = window.decorView
            // 两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
            val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility = option
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
//            if (com.fn.otc.util.StatusBarUtils.hasNotchInScreen(activity)) {
//                com.fn.otc.util.StatusBarUtils.setFullScreenWindowLayoutInDisplayCutout(window)
//            }
        } else {
            val window = activity.window
            val attributes = window.attributes
            val flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            val flagTranslucentNavigation =
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            attributes.flags = attributes.flags or flagTranslucentStatus
            //attributes.flags |= flagTranslucentNavigation;
            window.attributes = attributes
        }
    }

}