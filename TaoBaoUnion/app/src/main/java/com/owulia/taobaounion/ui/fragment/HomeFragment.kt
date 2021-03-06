package com.owulia.taobaounion.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.presenter.IHomePresenter
import com.owulia.taobaounion.presenter.impl.HomePresenterImpl
import com.owulia.taobaounion.ui.adapter.HomePagerAdapter
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.utils.PresenterManager
import com.owulia.taobaounion.view.IHomeCallback
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), IHomeCallback {

    private var mHomePresenter: IHomePresenter? = null
    private var homePagerAdapter: HomePagerAdapter? = null

    override fun getBaseViewResId() = R.layout.base_home_fragment_layout

    override fun getRootViewResId(): Int = R.layout.fragment_home

    override fun initView(view: View?) {
        super.initView(view)
        homePagerAdapter = HomePagerAdapter(childFragmentManager)
        LogUtil.d(this, "mHomePager => $mHomePager")
        mHomePager.adapter = homePagerAdapter
        mHomeIndicator.setupWithViewPager(mHomePager)
    }

    override fun initPresenter() {
        super.initPresenter()
        mHomePresenter = PresenterManager.instant.homePresenterImpl
        mHomePresenter?.registerViewCallback(this)
    }

    override fun loadData () {
        // 加载数据
        mHomePresenter?.getCategories()
    }

    override fun onRetry() {
        super.onRetry()
        // 网络错误点击重试
        // 重新加载
        loadData()
    }

    override fun onCategoriesLoaded(categories: Categories) {
        setUpState(State.SUCCESS)
        homePagerAdapter?.setCategories(categories)
//        mHomePager.offscreenPageLimit = categories.data.size
    }

    override fun onNetworkError() {
        setUpState(State.ERROR)
    }

    override fun onLoading() {
        setUpState(State.LOADING)
    }

    override fun onEmpty() {
        setUpState(State.EMPTY)
    }

    override fun release() {
        super.release()
        mHomePresenter?.unregisterViewCallback(this)
    }
}
