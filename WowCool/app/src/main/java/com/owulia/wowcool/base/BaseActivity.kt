package com.owulia.wowcool.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.owulia.wowcool.utils.WowStatusBarUtils

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getViewResourceId())
        // 设置导航条
        initStatusBar()
    }

    abstract fun getViewResourceId() : Int


    /**
     * 初始化状态栏
     * */
    open fun initStatusBar () {
        WowStatusBarUtils.with(this).setScreenFull()
    }
}