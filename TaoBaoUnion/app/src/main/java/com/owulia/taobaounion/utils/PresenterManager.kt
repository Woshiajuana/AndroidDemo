package com.owulia.taobaounion.utils

import com.owulia.taobaounion.presenter.impl.CategoryPagerPresenterImpl
import com.owulia.taobaounion.presenter.impl.HomePresenterImpl
import com.owulia.taobaounion.presenter.impl.TickPresenterImpl

class PresenterManager private constructor() {

    companion object {
        val instant by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { PresenterManager() }
    }

    val categoryPagerPresenterImpl = CategoryPagerPresenterImpl()
    val homePresenterImpl = HomePresenterImpl()
    val tickPresenterImpl = TickPresenterImpl()

}