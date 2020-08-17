package com.owulia.makekotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.owulia.makekotlin.R
import kotlinx.android.synthetic.main.widget_header.view.*

class NavBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var setOnLeftBtnClickListener: ((View) -> Unit)? = null
    var setOnRightBtnClickListener: ((View) -> Unit)? = null
    var setOnRightTextBtnClickListener: ((View) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_header, this, true)
        vNavBarLeftImgBtn.setOnClickListener{
            setOnLeftBtnClickListener?.let { it1 -> it1(it) }
        }
        vNavBarRightImgBtn.setOnClickListener{
            setOnRightBtnClickListener?.let { it1 -> it1(it) }
        }
        vNavBarRightTextBtn.setOnClickListener{
            setOnRightTextBtnClickListener?.let { it1 -> it1(it) }
        }
    }

    /**
     * 设置标题
     * @param title
     * */
    fun setTitle (title: String = "") {
        vNavBarTitle.text = title
    }

    /**
     * 设置左边 icon
     * @param resId
     * */
    fun setLeftImgBtn (resId: Int) {
        if (resId == -1) {

        }
    }

    /**
     * 设置左边按钮
     * @param visibility
     * */
    fun setLeftImgBtnVisibility (visibility: Int = View.GONE) {
        vNavBarLeftImgBtn.visibility = visibility
    }

    /**
     * 设置右边按钮
     * @param visibility
     * */
    fun setRightBtnImgVisibility (visibility: Int = View.GONE) {
        vNavBarRightImgBtn.visibility = visibility
    }

}