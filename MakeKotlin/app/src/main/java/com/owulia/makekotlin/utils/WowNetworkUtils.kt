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
    fun checkNetWorkState () : Int {
        val cm = content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        /**
         * 检测 API 是否小于21，因为 API 21之后 getNetworkInfo(int networkType) 方法被弃用
         * */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            /**获取 WIFI 连接信息*/
            val wifiNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            /**获取移动数据连接信息*/
            val mobileNetworkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo?.isConnected == true) {
                return NETWORK_WIFI
            } else if (mobileNetworkInfo?.isConnected == true) {
                return NETWORK_MOBILE
            } else {
                return NETWORK_NONE
            }
        }  else {
            val networks = cm.allNetworks
            networks.forEach {
                val netWorkInfo = cm.getNetworkInfo(it)
                if (netWorkInfo != null) {
                    if (netWorkInfo.type == ConnectivityManager.TYPE_WIFI) {
                        return NETWORK_WIFI
                    } else if (netWorkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                        return NETWORK_MOBILE
                    }
                }
            }
        }
        return NETWORK_NONE
    }

    fun test () {
        val cm = content.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks = cm.allNetworks
            networks.forEachIndexed { index, network ->
                WowLogUtils.d(this, "index => ${index}")
                WowLogUtils.d(this, "network => ${network}")
            }
        }
    }

}