package com.owulia.imoocmusicdemo.views

import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(space: Int) : RecyclerView.ItemDecoration() {

    private val _space = space

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = _space
//        if (parent.getChildLayoutPosition(view) % 3 != 0) {
//            outRect.left = _space
//        }
        val layoutParams = parent.layoutParams as LinearLayout.LayoutParams
        layoutParams.marginStart = -_space
        parent.layoutParams = layoutParams
    }
}