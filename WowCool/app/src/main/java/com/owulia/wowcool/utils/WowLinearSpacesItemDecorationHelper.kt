package com.owulia.wowcool.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class WowLinearSpacesItemDecorationHelper (
    private val leftSpace: Int = 0,
    private val topSpace: Int = 0,
    private val rightSpace: Int = 0,
    private val bottomSpace: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // 判断位置
        val position = parent.getChildAdapterPosition(view) // item position
        outRect.apply {
            left = leftSpace
            top = topSpace
            right = rightSpace
            bottom = bottomSpace
        }
    }

//    override fun getItemOffsets(
//        outRect: Rect,
//        view: View?,
//        parent: RecyclerView?,
//        state: RecyclerView.State?
//    ) {
//        super.getItemOffsets(outRect, view, parent!!, state!!)
//        outRect.top = topSpace
//        outRect.left = leftSpace
//        outRect.right = rightSpace
//        outRect.bottom = bottomSpace
//    }
}