package com.owulia.makekotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.widget_carousel.view.*

class WowCarousel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mAdapter : CarouselPagerAdapter? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_carousel, this, true)
    }

    /**
     * 设置适配器
     * @param adapter [PagerAdapter]
     * */
    fun setAdapter (adapter: CarouselPagerAdapter) {
        mAdapter = adapter
        vCarouselBox.adapter = mAdapter
    }

    /**
     * 内部适配器
     * */
    abstract class CarouselPagerAdapter : PagerAdapter () {

        abstract fun getInstantiateItem (position: Int) : View

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            return getInstantiateItem(position)
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