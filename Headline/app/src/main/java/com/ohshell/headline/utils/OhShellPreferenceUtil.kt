package com.ohshell.headline.utils

import android.content.Context
import android.preference.PreferenceManager

class OhShellPreferenceUtil(context: Context) {

//    val p: MMKV by lazy {
//        MMKV.defaultMMKV()!!
//    }

    private var context: Context

    init {
        // 保存上下文
        this.context = context.applicationContext
        // 不能这样写会导致内存泄露
//        this.context = context
//        PreferenceManager
    }


}