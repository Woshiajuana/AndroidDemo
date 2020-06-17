package com.owulia.taobaounion.view

import com.owulia.taobaounion.model.domain.Categories

interface IHomeCallback {

    /**
     *
     * */
    fun onCategoriesLoaded(categories: Categories)

    fun onNetworkError()

    fun onLoading()

    fun onEmpty()

}