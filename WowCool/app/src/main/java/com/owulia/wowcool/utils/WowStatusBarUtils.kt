package com.owulia.wowcool.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

object WowStatusBarUtils {

    /**
     * 设置全屏，设置状态栏透明
     * */
    fun setScreenFull(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
            val window = activity.window
            val decorView = window.decorView
            // 两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
            val option =
                (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            decorView.systemUiVisibility = option
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
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

    /**
     * 设置状态栏补充
     * @param parent [View] 父节点
     * */
    fun setStatusBarSeat(parent: View) {
        val view = View(parent.context)
    }

    /**
     * 获取状态栏高度
     * */
    fun getStatusBarHeight (context: Context): Int {
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 利用反射获取状态栏高度
     * */
    fun getStatusBarHeight (activity: Activity): Int {
        var statusHeight = 0
        var localClass: Class<*>? = null
        try {
            localClass = Class.forName("com.android.internal.R\$dimen")
            val localObject = localClass.newInstance()
            val h = localClass.getField("status_bar_height")[localObject].toString().toInt()
            statusHeight = activity.resources.getDimensionPixelSize(h)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return statusHeight
    }

    /**
     * 状态栏亮色模式，设置状态栏黑色文字、图标
     * @return 1:MIUUI 2:Flyme 3:android6.0
     * */
    fun setStatusBarLightMode(activity: Activity): Int {
        var result = 0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return result
        }
        when {
            mIUISetStatusBarLightMode(activity, true) -> {
                result = 1
            }
            flymeSetStatusBarLightMode(activity.window, true) -> {
                result = 2
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                activity.window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                result = 3
            }
        }

        return result
    }

    /**
     * 需要MIUIV6以上
     *
     * @param activity
     * @param dark     是否把状态栏文字及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private fun mIUISetStatusBarLightMode(activity: Activity, dark: Boolean): Boolean {
        var result = false
        val window = activity.window
        if (window != null) {
            val clazz: Class<*> = window.javaClass
            try {
                var darkModeFlag = 0
                val layoutParams =
                    Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field =
                    layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod(
                    "setExtraFlags",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag) //状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag) //清除黑色字体
                }
                result = true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
                    if (dark) {
                        activity.window.decorView.systemUiVisibility =
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    } else {
                        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    }
                }
            } catch (e: Exception) {
            }
        }
        return result
    }

    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏文字及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private fun flymeSetStatusBarLightMode(
        window: Window?,
        dark: Boolean
    ): Boolean {
        var result = false
        if (window != null) {
            try {
                val lp = window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java
                    .getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                value = if (dark) {
                    value or bit
                } else {
                    value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                window.attributes = lp
                result = true
            } catch (e: java.lang.Exception) {
            }
        }
        return result
    }
}