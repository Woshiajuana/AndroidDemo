package com.owulia.wowcool.base

import android.app.Application
import android.content.Context
import com.owulia.wowcool.utils.WowToastUtils

class BaseApplication : Application() {

    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = baseContext
        WowToastUtils.init(baseContext)
    }

}