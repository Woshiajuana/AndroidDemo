package com.owulia.taobaounion.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.SelectedPageCategory
import com.owulia.taobaounion.model.domain.SelectedPageContent
import com.owulia.taobaounion.presenter.impl.SelectedPagePresenter
import com.owulia.taobaounion.ui.adapter.SelectedCategoryAdapter
import com.owulia.taobaounion.ui.adapter.SelectedContentAdapter
import com.owulia.taobaounion.utils.LogUtil
import com.owulia.taobaounion.utils.PresenterManager
import com.owulia.taobaounion.view.ISelectedPageCallback
import kotlinx.android.synthetic.main.fragment_selected.*

/**
 * A simple [Fragment] subclass.
 */
class SelectedFragment : BaseFragment(), ISelectedPageCallback {


    private var mSelectedPagePresenter: SelectedPagePresenter? = null
    private var mSelectedCategoryAdapter: SelectedCategoryAdapter? = null
    private var mSelectedContentAdapter: SelectedContentAdapter? = null

    override fun getRootViewResId(): Int = R.layout.fragment_selected

    override fun initView(view: View?) {
        super.initView(view)
        mSelectedCategoryAdapter = SelectedCategoryAdapter()
        vCategory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mSelectedCategoryAdapter
        }
        vContent.apply {
            mSelectedContentAdapter = SelectedContentAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = mSelectedContentAdapter
        }
    }

    override fun initPresenter() {
        super.initPresenter()
        mSelectedPagePresenter = PresenterManager.instant.selectedPagePresenter
        mSelectedPagePresenter?.registerViewCallback(this)
        mSelectedPagePresenter?.getCategories()
    }

    override fun initListener() {
        super.initListener()
        mSelectedCategoryAdapter?.onItemClickListener = {
            LogUtil.d(this, " onItemClickListener => $it")
            mSelectedPagePresenter?.getContentByCategory(it)
        }
    }

    override fun release() {
        super.release()
        mSelectedPagePresenter?.unregisterViewCallback(this)
    }

    override fun onCategoriesLoaded(categories: SelectedPageCategory) {
        setUpState(State.SUCCESS)
//        TODO("Not yet implemented")
        LogUtil.d(this, "onCategoriesLoaded $categories")
        mSelectedCategoryAdapter?.setData(categories.data)
    }

    override fun onContentLoaded(content: SelectedPageContent) {
//        TODO("Not yet implemented")
        LogUtil.d(this, "onContentLoaded")
        mSelectedContentAdapter?.setData(content.data.tbk_uatm_favorites_item_get_response.results.uatm_tbk_item)
        vContent.scrollToPosition(0) // 滚动到顶部
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
}
