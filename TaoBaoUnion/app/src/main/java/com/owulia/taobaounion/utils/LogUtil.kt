package com.owulia.taobaounion.utils

import android.util.Log

object LogUtil {

    private const val COMMON = "LOG_UTIL"
    private var currentLev = 4
    private const val debugLev = 4
    private const val infoLev = 3
    private const val warningLev = 2
    private const val errorLev = 1

    fun d (clazz: Any, msg: String) {
        if (currentLev >= debugLev) {
            Log.d("$COMMON => ${clazz.javaClass.name}", msg)
        }
    }

    fun w (clazz: Any, msg: String) {
        if (currentLev >= warningLev) {
            Log.d("$COMMON => ${clazz.javaClass.name}", msg)
        }
    }

    fun i (clazz: Any, msg: String) {
        if (currentLev >= infoLev) {
            Log.d("$COMMON => ${clazz.javaClass.name}", msg)
        }
    }

    fun e (clazz: Any, msg: String) {
        if (currentLev >= errorLev) {
            Log.d("$COMMON => ${clazz.javaClass.name}", msg)
        }
    }

}