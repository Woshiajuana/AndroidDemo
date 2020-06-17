package com.owulia.taobaounion.presenter

import com.owulia.taobaounion.base.IBasePresenter
import com.owulia.taobaounion.view.ICategoryPagerCallback

interface ICategoryPagerPresenter : IBasePresenter<ICategoryPagerCallback> {
    // 根据 id 获取分类内容
    fun getContentByCategoryId(categoryId: Int)

    fun loaderMore(categoryId: Int)

    fun reload(categoryId: Int)

}