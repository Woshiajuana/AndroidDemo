package com.owulia.makekotlin.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.owulia.makekotlin.R
import com.owulia.makekotlin.utils.WowSizeUtils
import kotlinx.android.synthetic.main.widget_user_header.view.*

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
            val title = typedArray.getText(R.styleable.UserHeaderView_title)
            val subtitle = typedArray.getText(R.styleable.UserHeaderView_subtitle)

            val options = RequestOptions().circleCrop().transform(
                RoundedCorners(WowSizeUtils.px2dp(600f).toInt())
            )
            if (icon == -1) {
                vHeaderImg.visibility = View.GONE
            } else {
                Glide.with(this).load(icon).apply(options).into(vHeaderImg)
            }
            vHeaderTitle.text = title?: ""
            vHeaderSubtitle.text = subtitle?: ""

            typedArray.recycle()
        }
    }

}