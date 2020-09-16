package com.owulia.makekotlin.ui.activity

import com.owulia.makekotlin.R
import com.owulia.makekotlin.adapter.GuideViewPagerAdapter
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.model.GuidePageModel
import kotlinx.android.synthetic.main.activity_guide_page.*

class GuidePageActivity : BaseActivity() {

    override val isUseNavBar: Boolean = false

    override val isUseStatusBarSeat: Boolean = false

    override val isStatusBarLightMode: Boolean = false

    private val mArrGuidePageModel = arrayOf(
        GuidePageModel( bannerIcon = R.mipmap.ic_guide_one, textIcon = R.mipmap.ic_guide_text_one),
        GuidePageModel( bannerIcon = R.mipmap.ic_guide_two, textIcon = R.mipmap.ic_guide_text_two),
        GuidePageModel( bannerIcon = R.mipmap.ic_guide_three, textIcon = R.mipmap.ic_guide_text_three)
    )

    override fun getContentViewResourceId(): Int = R.layout.activity_guide_page

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)

        setStatusBarLightMode()

        setStatusBarDarkMode()

        vViewPager.apply {
            val guideViewPagerAdapter = GuideViewPagerAdapter(supportFragmentManager, mArrGuidePageModel).apply {
                offscreenPageLimit = 3
            }
            adapter = guideViewPagerAdapter
        }

    }

}
