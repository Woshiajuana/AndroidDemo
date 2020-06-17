package com.owulia.taobaounion.view

import com.owulia.taobaounion.model.domain.HomePagerContent

interface ICategoryPagerCallback {

    // 数据加载回来
    fun onContentLoad(contents: List<HomePagerContent.Data>)

    // 加载中
    fun onLoading (category: Int)

    // 错误
    fun onError (category: Int)

    // 数据为空
    fun onEmpty (category: Int)

    // 加载更多错误
    fun onLoadMoreError (category: Int)

    // 没有更多内容
    fun onLoadMoreEmpty (category: Int)

    // 加载更多成功
    fun onLoadMoreLoaded (contents: List<HomePagerContent.Data>)

    // 轮播图
    fun onLooperListLoaded (contents: List<HomePagerContent.Data>)

}