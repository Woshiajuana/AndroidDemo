package com.owulia.taobaounion.utils

import android.content.Context

class SizeUtil {
    companion object {
        val dp2px: (Context, Float) -> Int = { context, value ->
            val scale = context.resources.displayMetrics.density
            (value * scale + 0.5f).toInt()
        }
    }
}