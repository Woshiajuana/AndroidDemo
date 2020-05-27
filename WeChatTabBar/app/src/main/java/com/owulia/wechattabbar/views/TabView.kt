package com.owulia.wechattabbar.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.owulia.wechattabbar.R
import kotlinx.android.synthetic.main.tab_view.view.*

class TabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    
    init {
        val mView = LayoutInflater.from(context).inflate(R.layout.tab_view, this, false)
        addView(mView)
    }

    fun setProgress (progress: Float) {
        ivIcon.alpha = 1 - progress
        ivIconSelect.alpha = progress

    }
}