package com.owulia.taobaounion.view

import com.owulia.taobaounion.base.IBaseCallback
import com.owulia.taobaounion.model.domain.SelectedPageCategory
import com.owulia.taobaounion.model.domain.SelectedPageContent

interface ISelectedPageCallback : IBaseCallback{
    fun onCategoriesLoaded (categories: SelectedPageCategory)

    fun onContentLoaded (content: SelectedPageContent)
}