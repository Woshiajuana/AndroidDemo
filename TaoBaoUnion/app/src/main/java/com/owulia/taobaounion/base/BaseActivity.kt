package com.owulia.taobaounion.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initListener()
    }

    open fun initView() {}

    open fun initListener() {}

    abstract fun getLayoutResId(): Int
}