package com.owulia.makekotlin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment
import com.owulia.makekotlin.bean.GuidePageBean
import com.owulia.makekotlin.ui.activity.UserAccountActivity
import com.owulia.makekotlin.utils.Constants
import kotlinx.android.synthetic.main.fragment_guide_page.*

/**
 * A simple [Fragment] subclass.
 */
class GuidePageFragment : BaseFragment() {

    override val isUseNavBar: Boolean = false

    override val isUseStatusBarSeat: Boolean = false

    override val isStatusBarLightMode: Boolean = false

    companion object {
        fun getInstant (guidePageModel: GuidePageBean): GuidePageFragment {
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

        arguments?.apply {
            val guidePageModel = getParcelable<GuidePageBean>(Constants.KEY_GUIDE_PAGE_MODEL)
            guidePageModel?.apply {
                vImageIcon.setImageResource(bannerIcon)
                vImageText.setImageResource(textIcon)
                if (isLast) {
                    vIndicatorBox.visibility = View.GONE
                    vStartBox.visibility = View.VISIBLE
                } else {
                    vIndicatorBox.getChildAt(index).setBackgroundResource(R.drawable.shape_guide_active_indicator)
                }
                vContentBox.setBackgroundResource(bgColor)
            }
        }
    }

    override fun initListener() {
        super.initListener()
        vStartButton?.setOnClickListener {
            startActivity(Intent(context, UserAccountActivity::class.java))
            activity?.apply {
                overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
                finish()
            }
        }
    }



}
