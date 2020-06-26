package com.owulia.wowcool.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.owulia.wowcool.utils.WowLogUtils
import com.owulia.wowcool.utils.WowStatusBarUtils

abstract class BaseActivity : AppCompatActivity() {

    var vRootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vRootView = LayoutInflater.from(this).inflate(getViewResourceId(), null)
        setContentView(vRootView)
        // 设置状态栏
        initStatusBar()
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