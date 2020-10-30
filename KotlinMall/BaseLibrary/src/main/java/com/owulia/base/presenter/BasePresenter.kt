package com.owulia.base.presenter

import com.owulia.base.presenter.view.BaseView

open class BasePresenter<T: BaseView> {
    lateinit var mView: T
}