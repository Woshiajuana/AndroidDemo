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
     * @param msg [String]
     * */
    fun toast (msg: String)

    /**
     * 统一错误提示
     * @param msg [Int]
     * */
    fun toast (msg: Int)

    /**
     * 统一 code 错误
     * @param code [Int]
     * @return [Boolean] true: 继续向下传播  false: 不再向下传播
     * */
    fun callbackErrorCode (code: Int) = true
}