package com.owulia.makekotlin.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment
import com.owulia.makekotlin.widget.WowCarousel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    companion object {
        val instant: HomeFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { HomeFragment() }
    }

    override val mNavBarLeftImg: Int = -1

    override val mNavBarTitle: Int = R.string.string_tab_bar_home

    override val mNavBarRightImg: Int = R.mipmap.ic_message

    override fun getContentViewResourceId(): Int = R.layout.fragment_home

    override fun initView() {
        super.initView()
        vNavBar?.setNavBarTitleLeftAlign()
        render(RenderState.SUCCESS)
        vCarousel.setAdapter(object : WowCarousel.CarouselPagerAdapter() {
            override fun getInstantiateItem(position: Int): View {
                return View(context)
            }
        })
    }

}
