package com.owulia.wechattabbar.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.owulia.wechattabbar.R
import kotlinx.android.synthetic.main.tab_view.view.*


class TabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val colorDefault = Color.parseColor("#ff000000")
    val colorSelect = Color.parseColor("#ff007FD6")

    init {
        val mView = LayoutInflater.from(context).inflate(R.layout.tab_view, this, false)
        addView(mView)

        setProgress(0f)
    }

    fun setIconAndText (icon : Int, iconSelect : Int, text : String) {
        ivIcon.setImageResource(icon)
        ivIconSelect.setImageResource(iconSelect)
        tvTitle.text = text
    }

    fun setProgress (progress: Float) {
        ivIcon.alpha = 1 - progress
        ivIconSelect.alpha = progress
        tvTitle.setTextColor(evaluate(progress, colorDefault, colorSelect))
    }

    private fun evaluate (progress: Float, startValue: Int, endValue: Int) : Int {
        val startA = startValue shr 24 and 0xff
        val startR = startValue shr 16 and 0xff
        val startG = startValue shr 8 and 0xff
        val startB = startValue and 0xff

        val endA = endValue shr 24 and 0xff
        val endR = endValue shr 16 and 0xff
        val endG = endValue shr 8 and 0xff
        val endB = endValue and 0xff

        return (startA + progress.toInt() * (endA - startA)) as Int shl 24 or (
                (startR + progress.toInt() * (endR - startR)) as Int shl 16) or (
                (startG + progress.toInt() * (endG - startG)) as Int shl 8) or
                (startB + progress.toInt() * (endB - startB)) as Int
    }
}