package com.owulia.base.ui.activity

import com.owulia.base.presenter.BasePresenter
import com.owulia.base.presenter.view.BaseView
import javax.inject.Inject

open class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    @Inject
    lateinit var mPresenter: T
}