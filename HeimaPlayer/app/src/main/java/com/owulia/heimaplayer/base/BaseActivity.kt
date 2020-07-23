package com.owulia.heimaplayer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

abstract class BaseActivity: AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initListener()
        initData()
    }

    // 初始化数据
    open fun initData() {}

    // 事件
    open fun initListener() {}

    // 获取布局 id
    abstract fun getLayoutId(): Int

    // 弱提示
    open fun myToast (msg: String) {
        runOnUiThread { toast(msg) }
    }

}