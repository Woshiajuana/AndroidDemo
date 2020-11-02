package com.owulia.base.common

import android.app.Application
import com.owulia.base.injection.component.AppComponent
import com.owulia.base.injection.component.DaggerAppComponent
import com.owulia.base.injection.module.AppModule

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection ()
    }

    private fun initAppInjection () {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this))
            .build()
    }
}