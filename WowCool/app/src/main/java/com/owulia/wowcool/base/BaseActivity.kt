package com.owulia.wowcool.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.owulia.wowcool.utils.WowLogUtils
import com.owulia.wowcool.utils.WowStatusBarUtils

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewResourceId())
        // 设置导航条
        initStatusBar()
    }

    abstract fun getViewResourceId(): Int

    /**
     * 初始化状态栏
     * */
    open fun initStatusBar() {
        WowStatusBarUtils.apply {
            setStatusBarLightMode(this@BaseActivity)
            val height1 = getStatusBarHeight(this@BaseActivity)
            WowLogUtils.d(this, "height1 => $height1")
            val height2 = getStatusBarHeight(this@BaseActivity.baseContext)
            WowLogUtils.d(this, "height2 => $height2")
        }
    }
}