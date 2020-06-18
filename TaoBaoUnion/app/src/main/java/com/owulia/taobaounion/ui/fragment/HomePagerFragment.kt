package com.owulia.taobaounion.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.model.domain.HomePagerContent
import com.owulia.taobaounion.presenter.impl.CategoryPagerPresenterImpl
import com.owulia.taobaounion.utils.Constants
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.view.ICategoryPagerCallback

/**
 * A simple [Fragment] subclass.
 */
class HomePagerFragment : BaseFragment (), ICategoryPagerCallback {

    private var mCategoryPagerPresenterImpl: CategoryPagerPresenterImpl? = null
    private var mMaterialId : Int? = null

    companion object {
        val instant: (Categories.Data) -> HomePagerFragment = {
            val homePagerFragment = HomePagerFragment()
            val bundle = Bundle()
            bundle.putString(Constants.KEY_HOME_PAGER_TITLE, it.title)
            bundle.putInt(Constants.KEY_HOME_PAGER_MATERIAL_ID, it.id)
            homePagerFragment.arguments = bundle
            homePagerFragment
        }
    }

    override fun getRootViewResId() = R.layout.fragment_home_pager

    override fun initView(view: View?) {
        super.initView(view)
    }

    override fun initPresenter() {
        super.initPresenter()
        mCategoryPagerPresenterImpl = CategoryPagerPresenterImpl.instant
        mCategoryPagerPresenterImpl?.registerViewCallback(this)
    }

    override fun loadData() {
        super.loadData()
        val title = arguments?.getString(Constants.KEY_HOME_PAGER_TITLE)
        mMaterialId = arguments?.getInt(Constants.KEY_HOME_PAGER_MATERIAL_ID)
        // 加载数据
        LogUtil.d(this, "loadData materialId => $mMaterialId ")
        mCategoryPagerPresenterImpl?.getContentByCategoryId(mMaterialId!!)
    }

    override fun onContentLoad(contents: List<HomePagerContent.Data>, categoryId: Int) {
        // 数据列表加载
        if (mMaterialId != categoryId) return
        setUpState(State.SUCCESS)
    }

    override fun onLoading(categoryId: Int) {
        if (mMaterialId != categoryId) return
        setUpState(State.LOADING)
    }

    override fun onError(categoryId: Int) {
        // 网络错误
        if (mMaterialId != categoryId) return
        setUpState(State.ERROR)
    }

    override fun onEmpty(categoryId: Int) {
        if (mMaterialId != categoryId) return
        setUpState(State.EMPTY)
    }

    override fun onLoadMoreError(categoryId: Int) {
    }

    override fun onLoadMoreEmpty(categoryId: Int) {
    }

    override fun onLoadMoreLoaded(contents: List<HomePagerContent.Data>, categoryId: Int) {
    }

    override fun onLooperListLoaded(contents: List<HomePagerContent.Data>, categoryId: Int) {
    }

    override fun release() {
        super.release()
        LogUtil.d(this, "releasereleasereleasereleaserelease")
        mCategoryPagerPresenterImpl?.unregisterViewCallback(this)
    }
}
