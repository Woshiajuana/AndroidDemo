package com.owulia.testprovider

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

object SizeUtils {

    fun getScreenSize (context: Context): Point {
        val point = Point()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(point)
        return point
    }
}