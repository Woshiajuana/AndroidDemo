package com.ohshell.headline.utils

import android.os.Process

object OhShellProcessUtil {
    /**
     * 杀死当前应用进程
     * */
    fun killApp () {
        Process.killProcess(Process.myPid())
    }
}