package com.owulia.makekotlin.base

import android.app.Activity

interface IBaseView {

    /**
     * Presenter 中获取上下文对象
     * */
    fun getSelfActivity () : Activity

    /**
     * loading show
     * */
    fun loadingShow ()

    /**
     * loading hide
     * */
    fun loadingDismiss ()

    /**
     * 统一错误提示
     * */
    fun toast (msg: String)

    /**
     * 统一错误提示
     * */
    fun toast (msg: Int)

}