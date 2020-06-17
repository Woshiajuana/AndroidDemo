package com.owulia.taobaounion.view

import com.owulia.taobaounion.base.IBaseCallback
import com.owulia.taobaounion.model.domain.Categories

interface IHomeCallback : IBaseCallback {

    /**
     *
     * */
    fun onCategoriesLoaded(categories: Categories)

}