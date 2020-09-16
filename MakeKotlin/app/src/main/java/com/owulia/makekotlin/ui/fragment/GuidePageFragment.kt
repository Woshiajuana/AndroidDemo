package com.owulia.makekotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment
import com.owulia.makekotlin.model.GuidePageModel
import com.owulia.makekotlin.utils.Constants

/**
 * A simple [Fragment] subclass.
 */
class GuidePageFragment : BaseFragment() {

    override val isUseNavBar: Boolean = false

    override val isUseStatusBarSeat: Boolean = false

    override val isStatusBarLightMode: Boolean = false

    companion object {
        fun getInstant (guidePageModel: GuidePageModel): GuidePageFragment {
            val guidePageFragment = GuidePageFragment()
            val bundle = Bundle()
            bundle.putParcelable(Constants.KEY_GUIDE_PAGE_MODEL, guidePageModel)
            guidePageFragment.arguments = bundle
            return guidePageFragment
        }
    }

    override fun getContentViewResourceId(): Int = R.layout.fragment_guide_page

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
    }

}
