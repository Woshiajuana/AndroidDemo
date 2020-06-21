package com.owulia.taobaounion.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.owulia.taobaounion.R
import com.owulia.taobaounion.base.BaseFragment
import com.owulia.taobaounion.model.domain.SelectedPageCategory
import com.owulia.taobaounion.model.domain.SelectedPageContent
import com.owulia.taobaounion.presenter.impl.SelectedPagePresenter
import com.owulia.taobaounion.ui.adapter.SelectedCategoryAdapter
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

    override fun getRootViewResId(): Int = R.layout.fragment_selected

    override fun initView(view: View?) {
        super.initView(view)
        mSelectedCategoryAdapter = SelectedCategoryAdapter()
        vCategory.adapter = mSelectedCategoryAdapter
    }

    override fun initPresenter() {
        super.initPresenter()
        mSelectedPagePresenter = PresenterManager.instant.selectedPagePresenter
        mSelectedPagePresenter?.registerViewCallback(this)
        mSelectedPagePresenter?.getCategories()
    }

    override fun release() {
        super.release()
        mSelectedPagePresenter?.unregisterViewCallback(this)
    }

    override fun onCategoriesLoaded(categories: SelectedPageCategory) {
//        TODO("Not yet implemented")
        LogUtil.d(this, "onCategoriesLoaded")
        mSelectedCategoryAdapter?.setData(categories.data)
        val data = categories.data
        mSelectedPagePresenter?.getContentByCategory(data[0])
    }

    override fun onContentLoaded(content: SelectedPageContent) {
//        TODO("Not yet implemented")
        LogUtil.d(this, "onContentLoaded")
    }

    override fun onNetworkError() {
//        TODO("Not yet implemented")
    }

    override fun onLoading() {
//        TODO("Not yet implemented")
    }

    override fun onEmpty() {
//        TODO("Not yet implemented")
    }
}
