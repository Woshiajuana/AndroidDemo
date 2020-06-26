package com.owulia.wowcool.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.owulia.wowcool.R
import kotlinx.android.synthetic.main.view_navbar.view.*

class NavBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var setLeftBtnClickListener: ((View) -> Unit)? = null
    var setRightBtnClickListener: ((View) -> Unit)? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_navbar, this, false)
        orientation = VERTICAL
        addView(view)
        vNavBarLeftBtn.setOnClickListener {
            setLeftBtnClickListener?.let { it1 -> it1(it) }
        }
        vNavBarRightBtn.setOnClickListener{
            setRightBtnClickListener?.let { it1 -> it1(it) }
        }
    }

    // 设置标题
    fun setTitle (title: String = "") : NavBarView {
        vNavBarTitle.text = title
        return this
    }

    // 设置左边 icon
    fun setLeftImage (resId: Int = R.drawable.ic_arrow) : NavBarView {
        vNavBarLeftImage.setImageResource(resId)
        return this
    }

    // 设置右边 icon
    fun setRightImage (resId: Int) : NavBarView {
        vNavBarLeftImage.setImageResource(resId)
        return this
    }

    // 设置左边按钮
    fun setLeftBtnIsVisibility (visibility: Int = View.VISIBLE) {
        vNavBarLeftBtn.visibility = visibility
    }

    // 设置右边按钮
    fun setRightBtnIsVisibility (visibility: Int = View.GONE) {
        vNavBarLeftBtn.visibility = visibility
    }

}