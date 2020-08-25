package com.owulia.makekotlin.model

import android.view.View
import com.owulia.makekotlin.R

data class MenuCellModel(
    val labelText: Int? = null,
    val valueText: Int? = null,
    val prefixImg: Int? = null,
    val suffixImg: Int? = R.mipmap.ic_arrow_grey_right,
    val setOnClickListener: ((View) -> Unit)? = null
)