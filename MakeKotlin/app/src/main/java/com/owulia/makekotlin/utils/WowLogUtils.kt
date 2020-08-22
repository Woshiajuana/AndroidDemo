package com.owulia.makekotlin.utils

import android.util.Log

object WowLogUtils {

    private var currentLev = 4
    private const val COMMON = "WOW_LOG_UTIL"
    private const val DEBUG_LEV = 4
    private const val INFO_LEV = 3
    private const val WARNING_LEV = 2
    private const val ERROR_LEV = 1

    fun d (clazz: Any, msg: String) {
        if (currentLev >= DEBUG_LEV) {
            Log.d("$COMMON => ${clazz.javaClass.name}", msg)
        }
    }

    fun w (clazz: Any, msg: String) {
        if (currentLev >= WARNING_LEV) {
            Log.d("$COMMON => ${clazz.javaClass.name}", msg)
        }
    }

    fun i (clazz: Any, msg: String) {
        if (currentLev >= INFO_LEV) {
            Log.d("$COMMON => ${clazz.javaClass.name}", msg)
        }
    }

    fun e (clazz: Any, msg: String) {
        if (currentLev >= ERROR_LEV) {
            Log.d("$COMMON => ${clazz.javaClass.name}", msg)
        }
    }

}