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

    var setOnLeftBtnClickListener: ((View) -> Unit)? = null
    var setOnRightBtnClickListener: ((View) -> Unit)? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_navbar, this, false)
        orientation = VERTICAL
        addView(view)
        vNavBarLeftBtn.setOnClickListener {
            setOnLeftBtnClickListener?.let { it1 -> it1(it) }
        }
        vNavBarRightBtn.setOnClickListener{
            setOnRightBtnClickListener?.let { it1 -> it1(it) }
        }
    }

    // 设置标题
    fun setTitle (title: String = "") : NavBarView {
        vNavBarTitle.text = title
        return this
    }

    // 设置左边 icon
    fun setLeftImage (resId: Int) : NavBarView {
        if (resId == -1) {
            setLeftBtnIsVisibility()
        } else {
            vNavBarLeftImage.setImageResource(resId)
        }
        return this
    }

    // 设置右边 icon
    fun setRightImage (resId: Int) : NavBarView {
        if (resId == -1) {
            setRightBtnIsVisibility()
        } else {
            vNavBarRightImage.setImageResource(resId)
        }
        return this
    }

    // 设置左边按钮
    fun setLeftBtnIsVisibility (visibility: Int = View.GONE) : NavBarView {
        vNavBarLeftBtn.visibility = visibility
        return this
    }

    // 设置右边按钮
    fun setRightBtnIsVisibility (visibility: Int = View.GONE) : NavBarView {
        vNavBarRightBtn.visibility = visibility
        return this
    }

}