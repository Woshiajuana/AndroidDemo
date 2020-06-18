package com.owulia.taobaounion.base

import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = baseContext
    }

}