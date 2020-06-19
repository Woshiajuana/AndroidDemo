package com.owulia.taobaounion.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.widget.NestedScrollView

class TbNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    var mHeaderHeight = 0
    private var originScroll = 0


    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (originScroll < mHeaderHeight) {
            scrollBy(dx, dy)
            consumed[0] = dx
            consumed[1] = dy
        }
        super.onNestedPreScroll(target, dx, dy, consumed, type)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        originScroll = t
        super.onScrollChanged(l, t, oldl, oldt)
    }

}