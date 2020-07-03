package com.owulia.makekotlin

import android.app.Application
import com.owulia.makekotlin.adapter.ImageAdapter
import com.owulia.makekotlin.adapter.InterceptWXHttpAdapter
import com.owulia.makekotlin.adapter.JSExceptionAdapter
import org.apache.weex.InitConfig
import org.apache.weex.WXSDKEngine

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = InitConfig.Builder()
            .setImgAdapter(ImageAdapter())
            .setJSExceptionAdapter(JSExceptionAdapter())
            .setHttpAdapter(InterceptWXHttpAdapter())
            .build()
        WXSDKEngine.initialize(applicationContext as Application, config)
    }

}