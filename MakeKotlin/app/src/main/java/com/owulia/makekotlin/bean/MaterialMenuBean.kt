package com.owulia.makekotlin.bean

import android.view.View

data class MaterialMenuBean(
    val icon: Int? = null,
    val text: Int? = null,
    val setOnClickListener: ((View) -> Unit)? = null
)