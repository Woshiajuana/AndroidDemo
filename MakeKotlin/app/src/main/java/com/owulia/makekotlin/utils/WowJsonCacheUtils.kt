package com.owulia.makekotlin.utils

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
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
        private var mInstance: WowJsonCacheUtils? = null
        fun init (content: Context, name: String, mode: Int = Context.MODE_PRIVATE) {
            mContext = content
            mName = name
            mMode = mode
        }
        fun getInstance () = mInstance ?: synchronized(this) {
            if (mContext == null) throw Exception("WowJsonCacheUtils need Application context. You Should init ...")
            WowJsonCacheUtils(mContext!!, mName!!, mMode!!).also { mInstance = it }
        }
    }

    private var mSharedPreferences = context.getSharedPreferences(name, mode)

    /**
     * 保存缓存
     * @param key [String]
     * @param jsonObject [Any]
     * @param duration [Long]
     * */
    fun set (key: String, jsonObject: Any?, duration: Long = -1L) {
        val jsonString = Gson().toJson(jsonObject)
        val jsonCacheBean = JsonCacheBean(duration, jsonString)
        val jsonCacheBeanString = Gson().toJson(jsonCacheBean)
        mSharedPreferences?.edit()?.apply{
            putString(key, jsonCacheBeanString)
            apply()
        }
    }

    /**
     * 清除缓存
     * @param key [String]
     * */
    fun del (key: String) {
        mSharedPreferences?.edit()?.remove(key)?.apply()
    }

    /**
     * 获取缓存
     * @param key [String]
     * @param classOfT [Class<T>]
     * @param default [T]
     * @return [T]
     * */
    fun <T> get (key: String, classOfT: Class<T>, default: T ? = null) : T? {
        val jsonCacheBeanString = mSharedPreferences?.getString(key, "")
        if (TextUtils.isEmpty(jsonCacheBeanString)) {
            return default
        }
        val jsonCacheBean = Gson().fromJson(jsonCacheBeanString, JsonCacheBean::class.java)
        if (jsonCacheBean.duration != -1L && System.currentTimeMillis() - jsonCacheBean.duration >= 0) {
            return default
        }
        return Gson().fromJson(jsonCacheBean.json, classOfT)
    }

    data class JsonCacheBean (val duration: Long, val json: String)

}