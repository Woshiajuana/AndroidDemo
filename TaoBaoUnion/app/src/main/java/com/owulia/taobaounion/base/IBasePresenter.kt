package com.owulia.taobaounion.base

interface IBasePresenter<T> {

    // 注册 UI 通知接口
    fun registerViewCallback(callback: T)

    // 取消 UI 通知接口
    fun unregisterViewCallback(callback: T)

}