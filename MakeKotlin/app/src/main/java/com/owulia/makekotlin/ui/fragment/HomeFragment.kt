package com.owulia.makekotlin.ui.fragment

import android.graphics.*
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseFragment
import com.owulia.makekotlin.utils.WowSizeUtils
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

    val mArrBanner = listOf(
        R.mipmap.text_banner_1,
        R.mipmap.text_banner_2,
        R.mipmap.text_banner_3,
        R.mipmap.text_banner_4
    )

    override fun initView() {
        super.initView()
        vNavBar?.setNavBarTitleLeftAlign()
        render(RenderState.SUCCESS)
        vCarousel.start(object : WowCarousel.CarouselPagerAdapter() {
            override fun getInstantiateItem(position: Int): View {
                return ImageView(context).apply {
                    scaleType = ImageView.ScaleType.FIT_XY
                    val resId = mArrBanner[position]
                    val options = RequestOptions().circleCrop().transform(
                        RoundedCorners(WowSizeUtils.px2dp(2f).toInt())
                    )
                    Glide.with(this@HomeFragment)
                        .load(resId)
                        .apply(options)
                        .into(this)
                }
            }

            override fun getSize(): Int = mArrBanner.size
        })
    }

}
