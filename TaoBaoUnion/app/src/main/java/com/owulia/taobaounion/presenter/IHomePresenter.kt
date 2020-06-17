package com.owulia.taobaounion.presenter

import com.owulia.taobaounion.base.IBasePresenter
import com.owulia.taobaounion.view.IHomeCallback

interface IHomePresenter : IBasePresenter<IHomeCallback> {

    /**
     * 获取商品分类
     * */
    fun getCategories()

}