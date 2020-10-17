package com.owulia.makekotlin.utils

import android.app.Activity
import java.util.*

/**
 * Activity 管理类
 * */
class WowRouterManager private constructor () {

    val instant by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { WowRouterManager() }

    private val activityStack: Stack<Activity> = Stack<Activity>()

    /**
     * 添加到堆栈
     * @param activity [Activity]
     * */
    fun push(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 回退页面
     * */
    fun pop(index: Int) {

    }

    /**
     * 回到栈底，保留一个页面
     * */
    fun root() {

    }

    /**
     * 结束退出 App
     * */
    fun exit () {

    }

}