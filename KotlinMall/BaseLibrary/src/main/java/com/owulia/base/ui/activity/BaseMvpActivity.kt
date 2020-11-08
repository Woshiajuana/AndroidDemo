package com.owulia.base.ui.activity

import android.os.Bundle
import com.owulia.base.common.BaseApplication
import com.owulia.base.injection.component.ActivityComponent
import com.owulia.base.injection.component.DaggerActivityComponent
import com.owulia.base.injection.module.ActivityModule
import com.owulia.base.injection.module.LifecycleProviderModule
import com.owulia.base.presenter.BasePresenter
import com.owulia.base.presenter.view.BaseView
import com.owulia.base.widgets.ProgressLoading
import javax.inject.Inject

abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError() {
    }

    @Inject
    lateinit var mPresenter: T

    private lateinit var mLoadingDialog: ProgressLoading

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()

        mLoadingDialog = ProgressLoading.create(this)
    }

    abstract fun injectComponent ()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((application as BaseApplication).appComponent)
            .activityModule(ActivityModule(this))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }

}