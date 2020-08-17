package com.owulia.makekotlin.utils

import android.content.res.Resources
import android.util.TypedValue

object WowSizeUtils {
    fun px2dp(p: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        p,
        Resources.getSystem().displayMetrics
    )
}