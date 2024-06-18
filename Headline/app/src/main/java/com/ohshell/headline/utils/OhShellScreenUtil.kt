package com.ohshell.headline.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object OhShellScreenUtil {
    fun getScreenWidth(context: Context): Int {
        // 后去 window 管理器
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        // 创建显示对象
        val displayMetrics = DisplayMetrics()
        // 获取默认显示对象
        wm.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        // 后去 window 管理器
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        // 创建显示对象
        val displayMetrics = DisplayMetrics()
        // 获取默认显示对象
        wm.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}