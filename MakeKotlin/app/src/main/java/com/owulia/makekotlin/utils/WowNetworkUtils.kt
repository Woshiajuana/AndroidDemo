package com.owulia.makekotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
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
     * @return [Boolean]
     * */
    fun isConnected () : Boolean {
        val cm = content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /**获取网络属性*/
            val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (networkCapabilities != null) {
                return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            }
        } else {
            return cm.activeNetworkInfo?.isAvailable == true
        }
        return false
    }

    /**
     * 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     * 直接 ping
     * 看结果
     * */
    fun isOnline () : Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("ping -c 3 www.baidu.com")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 检测网络类型
     * */

}