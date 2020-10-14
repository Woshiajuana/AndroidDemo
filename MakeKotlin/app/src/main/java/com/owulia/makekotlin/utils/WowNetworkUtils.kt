package com.owulia.makekotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.lang.Exception

class WowNetworkUtils private constructor (
    private val content: Context
) {

    companion object {
        private var mInstance: WowNetworkUtils? = null
        private var mContext: Context? = null
        fun init (content: Context) {
            mContext = content
        }
        fun getInstance () = mInstance ?: synchronized(this) {
            if (mContext == null) throw Exception("WowNetworkUtils need Application context. You Should init ...")
            WowNetworkUtils(mContext!!).also { mInstance = it }
        }
    }

    fun getNetworkInfo () : NetworkInfo {
        val cm = content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetworkInfo
    }

    /**
     * 判断网络是否可用
     * <p>需添加权限 android.permission.ACCESS_NETWORK_STATE</p>
     * */
    fun isAvailable () : Boolean {
        val cm = content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.activeNetworkInfo.isAvailable
        return false
    }

}