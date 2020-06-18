package com.owulia.taobaounion.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.Categories
import com.owulia.taobaounion.model.domain.HomePagerContent
import com.owulia.taobaounion.presenter.impl.CategoryPagerPresenterImpl
import com.owulia.taobaounion.ui.adapter.HomePagerContentAdapter
import com.owulia.taobaounion.ui.adapter.LoopPagerAdapter
import com.owulia.taobaounion.utils.Constants
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.view.ICategoryPagerCallback
import kotlinx.android.synthetic.main.fragment_home_pager.*

/**
 * A simple [Fragment] subclass.
 */
class HomePagerFragment : BaseFragment (), ICategoryPagerCallback {

    private var mCategoryPagerPresenterImpl: CategoryPagerPresenterImpl? = null
    private var mMaterialId : Int? = null
    private var mHomePagerContentAdapter : HomePagerContentAdapter? = null
    private var mLoopPagerAdapter : LoopPagerAdapter? = null

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
        // 设置布局管理器
        // 创建适配器
        // 设置适配器
        mContentList.apply {
            mHomePagerContentAdapter = HomePagerContentAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = mHomePagerContentAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration () {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top = 10
                    outRect.bottom = 10
                }
            })
        }

        mLoopPager.apply {
            mLoopPagerAdapter = LoopPagerAdapter()
            adapter = mLoopPagerAdapter
        }
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
        mHomePagerContentAdapter?.setData(contents)
        setUpState(State.SUCCESS)
    }

    override fun onLoading() {
        setUpState(State.LOADING)
    }

    override fun onNetworkError() {
        setUpState(State.ERROR)
    }

    override fun onEmpty() {
        setUpState(State.EMPTY)
    }

    override fun onLoadMoreError(categoryId: Int) {
    }

    override fun onLoadMoreEmpty(categoryId: Int) {
    }

    override fun onLoadMoreLoaded(contents: List<HomePagerContent.Data>, categoryId: Int) {
    }

    override fun getCategoryId(): Int? {
        return mMaterialId
    }

    override fun onLooperListLoaded(contents: List<HomePagerContent.Data>, categoryId: Int) {
        mLoopPagerAdapter?.setData(contents)
    }

    override fun release() {
        super.release()
        mCategoryPagerPresenterImpl?.unregisterViewCallback(this)
    }
}
