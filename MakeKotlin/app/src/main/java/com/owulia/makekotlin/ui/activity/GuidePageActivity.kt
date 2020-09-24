package com.owulia.makekotlin.ui.activity

import com.owulia.makekotlin.R
import com.owulia.makekotlin.adapter.GuideViewPagerAdapter
import com.owulia.makekotlin.base.BaseActivity
import com.owulia.makekotlin.bean.GuidePageBean
import com.owulia.makekotlin.utils.Constants
import com.owulia.makekotlin.utils.WowJsonCacheUtils
import kotlinx.android.synthetic.main.activity_guide_page.*

class GuidePageActivity : BaseActivity() {

    override val isUseNavBar: Boolean = false

    override val isUseStatusBarSeat: Boolean = false

    override val isStatusBarLightMode: Boolean = false

    private val mArrGuidePageModel = arrayOf(
        GuidePageBean(
            index = 0,
            bgColor = R.color.colorGuideOne,
            bannerIcon = R.mipmap.ic_guide_one,
            textIcon = R.mipmap.ic_guide_text_one
        ),
        GuidePageBean(
            index = 1,
            bgColor = R.color.colorGuideTwo,
            bannerIcon = R.mipmap.ic_guide_two,
            textIcon = R.mipmap.ic_guide_text_two
        ),
        GuidePageBean(
            index = 2,
            isLast = true,
            bgColor = R.color.colorGuideThree,
            bannerIcon = R.mipmap.ic_guide_three,
            textIcon = R.mipmap.ic_guide_text_three
        )
    )

    override fun getContentViewResourceId(): Int = R.layout.activity_guide_page

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)

        initFirstOpen()

        vViewPager.apply {
            val guideViewPagerAdapter = GuideViewPagerAdapter(supportFragmentManager, mArrGuidePageModel).apply {
                offscreenPageLimit = 3
            }
            adapter = guideViewPagerAdapter
        }

    }

    /**
     * 载入数据 用户已经打开过 app
     */
    private fun initFirstOpen () {
        WowJsonCacheUtils.getInstance().set(Constants.JSON_CACHE_KEY_FIRST_OPEN, false)
    }

}
