package com.owulia.taobaounion.presenter

import com.owulia.taobaounion.view.IHomeCallback

interface IHomePresenter {

    /**
     * 获取商品分类
     * */
    fun getCategories()

    // 注册 UI 通知接口
    fun registerCallback(callback: IHomeCallback)

    // 取消 UI 通知接口
    fun unregisterCallback(callback: IHomeCallback)

}