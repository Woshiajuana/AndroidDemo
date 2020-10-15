package com.owulia.makekotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.lang.Exception

class WowNetworkUtils private constructor (
    private val content: Context
) {

    companion object {

        /**
         * 没有连接网络
         * */
        const val NETWORK_NONE = -1

        /**
         * 移动网络
         * */
        const val NETWORK_MOBILE = 0

        /**
         * 无线网络
         * */
        const val NETWORK_WIFI = 1

        /**
         * 蓝牙网络
         * */
        const val NETWORK_BLUETOOTH = 2

        /**
         * 以太网
         * */
        const val NETWORK_ETHERNET = 3

        /**
         * VPN
         * */
        const val NETWORK_VPN = 4

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

    /**
     * 判断网络是否可用
     * <p>需添加权限 android.permission.ACCESS_NETWORK_STATE</p>
     * @return [Boolean] true: 已连接 false: 未连接
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
     * @return [Boolean] true: 已连接 false: 未连接
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
     * Wifi 是否已连接
     * @return [Boolean] true: 是 false: 否
     * */
    @Suppress("DEPRECATION")
    fun isWifiConnected () : Boolean {
        val cm = content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (networkCapabilities != null) {
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        } else {
            val networkInfo = cm.activeNetworkInfo
            return networkInfo?.isConnected == true && networkInfo.type == ConnectivityManager.TYPE_WIFI
        }
        return false
    }

    /**
     * 是否流量
     * @return [Boolean] true: 是 false: 否
     * */
    @Suppress("DEPRECATION")
    fun isMobileData () : Boolean {
        val cm = content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (networkCapabilities != null) {
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            }
        } else {
            val networkInfo = cm.activeNetworkInfo
            return networkInfo?.isConnected == true && networkInfo.type == ConnectivityManager.TYPE_MOBILE
        }
        return false
    }

    /**
     * 检测网络类型
     * @return [Int]
     * */
    @Suppress("DEPRECATION")
    fun checkNetWorkState () : Int {
        val cm = content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (networkCapabilities != null) {
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return NETWORK_MOBILE
                }
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return NETWORK_WIFI
                }
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)) {
                    return NETWORK_BLUETOOTH
                }
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return NETWORK_ETHERNET
                }
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    return NETWORK_VPN
                }
            }
        }  else {
            val networkInfo = cm.activeNetworkInfo
            if (networkInfo != null) {
                if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                    return NETWORK_MOBILE
                }
                if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    return NETWORK_WIFI
                }
                if (networkInfo.type == ConnectivityManager.TYPE_BLUETOOTH) {
                    return NETWORK_BLUETOOTH
                }
                if (networkInfo.type == ConnectivityManager.TYPE_ETHERNET) {
                    return NETWORK_ETHERNET
                }
                if (networkInfo.type == ConnectivityManager.TYPE_VPN) {
                    return NETWORK_VPN
                }
            }
        }
        return NETWORK_NONE
    }
}