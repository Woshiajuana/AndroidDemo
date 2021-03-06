package com.owulia.taobaounion.view

import com.owulia.taobaounion.base.IBaseCallback
import com.owulia.taobaounion.model.domain.HomePagerContent

interface ICategoryPagerCallback : IBaseCallback {

    // 数据加载回来
    fun onContentLoad(contents: List<HomePagerContent.Data>, categoryId: Int)

    // 加载更多错误
    fun onLoadMoreError(categoryId: Int)

    // 没有更多内容
    fun onLoadMoreEmpty(categoryId: Int)

    // 加载更多成功
    fun onLoadMoreLoaded(
        contents: List<HomePagerContent.Data>,
        categoryId: Int
    )

    fun getCategoryId() : Int?

    // 轮播图
    fun onLooperListLoaded(
        contents: List<HomePagerContent.Data>,
        categoryId: Int
    )

}