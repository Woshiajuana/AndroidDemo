package com.owulia.makekotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.widget_carousel.view.*

class WowCarousel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_carousel, this, true)

        vCarouselBox.apply {
            adapter = MyPagerAdapter()
        }
    }
}

class MyPagerAdapter : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val vText = TextView(container.context).apply {
            text = position.toString()
        }
        container.addView(vText)
        return vText
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun getCount(): Int {
        return 3
    }
}