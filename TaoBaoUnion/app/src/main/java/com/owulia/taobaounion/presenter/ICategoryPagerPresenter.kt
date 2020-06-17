package com.owulia.taobaounion.presenter

interface ICategoryPagerPresenter {
    // 根据 id 获取分类内容
    fun getContentByCategoryId(categoryId: Int)

    fun loaderMore(categoryId: Int)

    fun reload(categoryId: Int)


}