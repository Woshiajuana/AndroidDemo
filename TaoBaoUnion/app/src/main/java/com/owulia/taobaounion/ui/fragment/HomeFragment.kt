package com.owulia.taobaounion.ui.fragment

import androidx.fragment.app.Fragment
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.presenter.IHomePresenter
import com.owulia.taobaounion.presenter.impl.HomePresenterImpl
import com.owulia.taobaounion.view.IHomeCallback

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), IHomeCallback {

    private lateinit var mHomePresenter: IHomePresenter

    override fun getRootViewResId(): Int = R.layout.fragment_home

    override fun initPresenter() {
        super.initPresenter()
        mHomePresenter = HomePresenterImpl()
        mHomePresenter.registerCallback(this)
    }

    override fun loadData () {
        // 加载数据
        mHomePresenter.getCategories()
    }

    override fun onCategoriesLoaded(categories: Categories) {

    }

    override fun release() {
        super.release()
        mHomePresenter.unregisterCallback(this)
    }
}
