package com.owulia.taobaounion.presenter

import com.owulia.taobaounion.base.IBasePresenter
import com.owulia.taobaounion.model.domain.SelectedPageCategory
import com.owulia.taobaounion.view.ISelectedPageCallback

interface ISelectedPagePresenter : IBasePresenter<ISelectedPageCallback> {

    fun getCategories()

    fun getContentByCategory(item : SelectedPageCategory.Data)

    fun reloadContent()

}