package com.owulia.wowcool.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.owulia.wowcool.utils.AndroidBug5497Workaround
import com.owulia.wowcool.utils.WowLogUtils
import com.owulia.wowcool.utils.WowStatusBarUtils

abstract class BaseActivity : AppCompatActivity() {

    var vRootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        vRootView = LayoutInflater.from(this).inflate(getViewResourceId(), null)
        setContentView(vRootView)
        // 设置状态栏
        initStatusBar()
        AndroidBug5497Workaround.assistActivity(this)
    }

    abstract fun getViewResourceId(): Int

    /**
     * 初始化状态栏
     * */
    open fun initStatusBar() {
        WowStatusBarUtils.apply {
            setStatusBarLightMode(this@BaseActivity) // 设置状态栏黑色文字、图标
        }
    }
}