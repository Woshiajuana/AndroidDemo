package com.owulia.taobaounion.presenter

import com.owulia.taobaounion.view.IHomeCallback

interface IHomePresenter {

    /**
     * 获取商品分类
     * */
    fun getCategories()

    fun registerCallback(callback: IHomeCallback)

    fun unregisterCallback(callback: IHomeCallback)

}