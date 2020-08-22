package com.owulia.makekotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.owulia.makekotlin.R
import com.owulia.makekotlin.utils.WowSizeUtils
import kotlinx.android.synthetic.main.widget_carousel.view.*

class WowCarousel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mAdapter: CarouselPagerAdapter? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_carousel, this, true)
    }

    /**
     * @param adapter [PagerAdapter]
     * */
    fun start(adapter: CarouselPagerAdapter) {
        mAdapter = adapter
        setAdapter()
        setIndicator()
        setListener()
    }

    /**
     * 设置适配器
     * */
    private fun setAdapter() {
        mAdapter?.let {
            vCarouselBox.apply {
                adapter = it
                val numMiddle = Int.MAX_VALUE / 2
                val numOffset = it.getSize() - numMiddle % it.getSize()
                currentItem = numMiddle + numOffset
            }
        }
    }

    /**
     * 设置监听
     * */
    private fun setListener() {
        vCarouselBox.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setIndicator()
            }
        })
    }

    /**
     * 设置指示器 or 更新指示器
     * */
    private fun setIndicator() {
        mAdapter?.apply {
            vIndicatorBox.removeAllViews()
            val size = getSize()
            val currPosition = vCarouselBox.currentItem % size
            for (i in 0 until size) {
                val vView = View(context)
                val nMargin = WowSizeUtils.px2dp(3f).toInt()
                if (currPosition == i) {
                    vView.apply {
                        layoutParams = LayoutParams(
                            WowSizeUtils.px2dp(20f).toInt(),
                            WowSizeUtils.px2dp(8f).toInt()
                        ).apply {
                            leftMargin = nMargin
                            rightMargin = nMargin
                        }
                        setBackgroundResource(R.drawable.shape_indicator_selected)
                    }
                } else {
                    vView.apply {
                        layoutParams = LayoutParams(
                            WowSizeUtils.px2dp(8f).toInt(),
                            WowSizeUtils.px2dp(8f).toInt()
                        ).apply {
                            leftMargin = nMargin
                            rightMargin = nMargin
                        }
                        setBackgroundResource(R.drawable.shape_indicator_unselected)
                    }
                }
                vIndicatorBox.addView(vView)
            }
        }
    }

    /**
     * 内部适配器
     * */
    abstract class CarouselPagerAdapter : PagerAdapter() {

        abstract fun getInstantiateItem(position: Int): View

        abstract fun getSize(): Int

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = getInstantiateItem(position % getSize());
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return Int.MAX_VALUE
        }
    }
}