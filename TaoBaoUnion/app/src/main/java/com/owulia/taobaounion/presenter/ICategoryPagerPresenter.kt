package com.owulia.taobaounion.presenter

import com.owulia.taobaounion.base.IBasePresenter

interface ICategoryPagerPresenter : IBasePresenter<ICategoryPagerPresenter> {
    // 根据 id 获取分类内容
    fun getContentByCategoryId(categoryId: Int)

    fun loaderMore(categoryId: Int)

    fun reload(categoryId: Int)

}