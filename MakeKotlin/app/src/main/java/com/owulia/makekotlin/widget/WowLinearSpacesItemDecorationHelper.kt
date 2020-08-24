package com.owulia.makekotlin.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class WowLinearSpacesItemDecorationHelper (
    private val leftSpace: Int = 0,
    private val topSpace: Int = 0,
    private val rightSpace: Int = 0,
    private val bottomSpace: Int = 0,
    private val childCount: Int = -1
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
            left = if (childCount != -1 && position == 0) leftSpace * 2 else leftSpace
            top = topSpace
            right = if (position == childCount - 1) rightSpace * 2 else rightSpace
            bottom = bottomSpace
        }
    }
}