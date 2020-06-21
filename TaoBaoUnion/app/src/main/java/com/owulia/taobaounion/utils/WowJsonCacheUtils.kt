package com.owulia.taobaounion.utils

import android.content.Context
import java.lang.Exception

class WowJsonCacheUtils private constructor(
    private val context: Context,
    private val name: String,
    private val mode: Int
) {

    companion object {
        private var mContext: Context? = null
        private var mName: String? = null
        private var mMode: Int? = null
        fun init (content: Context, name: String, mode: Int = Context.MODE_PRIVATE) {
            mContext = content
            mName = name
            mMode = mode
        }
        private var instance: WowJsonCacheUtils? = null
        var INSTANCE = instance ?: synchronized(this) {
            if (mContext == null) throw Exception("WowJsonCacheUtils need Application content. Should init ...")
            WowJsonCacheUtils(mContext!!, mName!!, mMode!!).also { instance = it }
        }
    }

    private var mSharedPreferences = context.getSharedPreferences(name, mode)

    fun save (key: String, jsonObject: Any?) {

    }

}