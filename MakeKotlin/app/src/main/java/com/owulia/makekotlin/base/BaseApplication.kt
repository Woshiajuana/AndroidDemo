package com.owulia.makekotlin.base

import android.app.Application
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowJsonCacheUtils
import com.owulia.makekotlin.utils.WowNetworkUtils
import com.owulia.makekotlin.utils.WowToastUtils

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        build(this)
    }

    /**
     * 初始化
     * */
    fun build (application: Application) {
        WowJsonCacheUtils.init(application, Constants.JSON_CACHE_KEY_COMMON)
        WowToastUtils.init(application)
        WowNetworkUtils.init(application)
    }
}