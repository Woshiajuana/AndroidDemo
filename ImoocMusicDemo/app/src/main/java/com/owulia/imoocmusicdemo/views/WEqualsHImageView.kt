package com.owulia.imoocmusicdemo.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class WEqualsHImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
//        // 获取 View 的宽度
//        val width = MeasureSpec.getSize(widthMeasureSpec)
//        // 获取 View 的模式 match_parent 、 wrap_parent 、具体的 dp
//        val mode = MeasureSpec.getMode(widthMeasureSpec)
    }

}