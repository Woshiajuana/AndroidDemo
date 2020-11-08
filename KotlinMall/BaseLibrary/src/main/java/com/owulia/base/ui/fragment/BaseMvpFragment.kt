package com.owulia.base.ui.fragment

import android.os.Bundle
import com.owulia.base.common.BaseApplication
import com.owulia.base.injection.component.ActivityComponent
import com.owulia.base.injection.component.DaggerActivityComponent
import com.owulia.base.injection.module.ActivityModule
import com.owulia.base.injection.module.LifecycleProviderModule
import com.owulia.base.presenter.BasePresenter
import com.owulia.base.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()
    }

    abstract fun injectComponent ()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((activity!!.application as BaseApplication).appComponent)
            .activityModule(ActivityModule(activity!!))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }

}