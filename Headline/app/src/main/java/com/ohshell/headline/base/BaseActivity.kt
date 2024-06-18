package com.ohshell.headline.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    /**
     * 初始化页面
     * */
    protected open fun initView() {}


    /**
     * 初始化数据
     * */
    protected open fun initData() {}


    /**
     * 设置监听器
     * */
    protected open fun initListeners () {}

    /**
     * 在 onCreate 方法后面调用
     * @param savedInstanceState
     * */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initView()
        initData()
        initListeners()
    }
}