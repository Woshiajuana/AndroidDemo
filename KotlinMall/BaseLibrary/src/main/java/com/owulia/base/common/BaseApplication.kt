package com.owulia.base.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.owulia.base.injection.component.AppComponent
import com.owulia.base.injection.component.DaggerAppComponent
import com.owulia.base.injection.module.AppModule

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        initAppInjection ()

        context = this

        ARouter.openLog() // 打印日志
        ARouter.openDebug() // 开启调试模式
        ARouter.init(this)
    }

    private fun initAppInjection () {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this))
            .build()
    }
}