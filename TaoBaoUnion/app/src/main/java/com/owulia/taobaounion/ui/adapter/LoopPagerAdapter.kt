package com.owulia.taobaounion.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.owulia.taobaounion.model.domain.HomePagerContent
import com.owulia.taobaounion.utils.UrlUtil

class LoopPagerAdapter : PagerAdapter () {

    var data = ArrayList<HomePagerContent.Data>()

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val realPosition = position % data.size
        val iv = ImageView(container.context)
        val itemData = data[realPosition]
        val size = container.measuredWidth / 2
        val url = UrlUtil.getCoverPath(itemData.pict_url, size)
        iv.apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        Glide.with(container.context)
            .load(url)
            .into(iv)
        container.addView(iv)
        return iv
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    fun setData(contents: List<HomePagerContent.Data>) {
        data.clear()
        data.addAll(contents)
        notifyDataSetChanged()
    }
}