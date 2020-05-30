package com.owulia.wowcool

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager

class WowTabBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

//    private var viewPager

    init {

        // 初始化
        val vViewPager = ViewPager(context)

        orientation = VERTICAL

//        var v = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
//        v.weight = 1f
        (vViewPager.layoutParams as LayoutParams).apply {
            weight = 1f
        }
        vViewPager.setBackgroundColor(Color.parseColor("#ffff0000"))

        var vText = TextView(context)
        vText.text = "你好呀"



        addView(vViewPager)
        addView(vText)


    }

    // 初始化 ViewPager
    private fun renderViewPager () {
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        layoutParams.apply {
            weight = 1f
        }
    }


}