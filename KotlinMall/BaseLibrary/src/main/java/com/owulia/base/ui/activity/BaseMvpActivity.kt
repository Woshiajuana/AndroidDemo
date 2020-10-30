package com.owulia.base.ui.activity

import com.owulia.base.presenter.BasePresenter
import com.owulia.base.presenter.view.BaseView

open class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    lateinit var mPresenter: T
}