package com.owulia.makekotlin.utils

import android.app.Activity
import java.util.*
import kotlin.system.exitProcess

/**
 * Activity 管理类
 * */
class WowRouterManager private constructor () {

    companion object {
        val instant by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { WowRouterManager() }
    }

    private val activityStack: Stack<Activity> = Stack<Activity>()

    /**
     * 添加到堆栈
     * @param activity [Activity]
     * */
    fun add(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 移出
     * */
    fun remove (activity: Activity?) {
        activity?.let {
            if (activityStack.contains(it)) {
                activityStack.remove(it)
            }
            it.finish()
        }
    }

    /**
     * 移出
     * 假如界面共8页 [1（首页）, 2, 3, 4, 5, 6, 7, 8（当前页）]
     * <p>
     * back（1）回到7  不处理
     * back（8）回到8  不处理
     * back（3）回到5  移除 6.7
     * back（2）回到6  移除 7
     * @param  index [Int]
     * */
    fun remove (index: Int) {
        for (i in 0 until index) {
            if (activityStack.size > 2) {
                remove(activityStack[activityStack.size - 1])
            }
        }
    }

    /**
     * 重定向到一个页面
     * */
    fun redirect () {
        activityStack.forEachIndexed { index, activity ->
            if (index != activityStack.size - 1) {
                remove(activity)
            }
        }
    }

    /**
     * 回退页面
     * */
    fun pop(index: Int = 1) {
        for (i in 0 until index) {
            if (activityStack.size > 1) {
                remove(activityStack.pop())
            }
        }
    }

    /**
     * 回到栈底，保留一个页面
     * */
    fun root() {
        activityStack.forEachIndexed { index, activity ->
            if (index != 0) {
                remove(activity)
            }
        }
    }

    /**
     * 结束退出 App
     * */
    fun exit () {
        try {
            activityStack.forEach{
                remove(it)
            }
            exitProcess(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}