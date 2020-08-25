package com.owulia.makekotlin.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.owulia.makekotlin.R
import com.owulia.makekotlin.utils.WowLogUtils
import com.owulia.makekotlin.utils.WowSizeUtils

class UserHeaderView @JvmOverloads constructor(
    context: Context, private val attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_user_header, this, true)
        setPadding(WowSizeUtils.px2dp(20f).toInt(), 0, WowSizeUtils.px2dp(20f).toInt(),0)
        initView()
    }

    private fun initView () {
        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.UserHeaderView)

            val icon = typedArray.getResourceId(R.styleable.UserHeaderView_icon, -1)
            val title = typedArray.getTextArray(R.styleable.UserHeaderView_title)
            val subtitle = typedArray.getTextArray(R.styleable.UserHeaderView_subtitle)

            WowLogUtils.d(this, "icon => $icon")
            WowLogUtils.d(this, "title => $title")
            WowLogUtils.d(this, "subtitle => $subtitle")

            typedArray.recycle()
        }
    }

}