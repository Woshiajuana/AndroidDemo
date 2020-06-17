package com.owulia.taobaounion.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.presenter.IHomePresenter
import com.owulia.taobaounion.presenter.impl.HomePresenterImpl
import com.owulia.taobaounion.ui.adapter.HomePagerAdapter
import com.owulia.taobaounion.view.IHomeCallback
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), IHomeCallback {

    private var mHomePresenter: IHomePresenter? = null
    private var homePagerAdapter: HomePagerAdapter? = null

    override fun getRootViewResId(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        super.initView(view)
        mHomeIndicator.setupWithViewPager(mHomePager)
        homePagerAdapter = HomePagerAdapter(childFragmentManager)
    }

    override fun initPresenter() {
        super.initPresenter()
        mHomePresenter = HomePresenterImpl()
        mHomePresenter?.registerCallback(this)
    }

    override fun loadData () {
        // 加载数据
        mHomePresenter?.getCategories()
    }

    override fun onCategoriesLoaded(categories: Categories) {
        homePagerAdapter?.setCategories(categories)
    }

    override fun release() {
        super.release()
        mHomePresenter?.unregisterCallback(this)
    }
}
