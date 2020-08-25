package com.owulia.makekotlin.model

import android.view.View

data class MaterIalMenuModel(
    val icon: Int? = null,
    val text: Int? = null,
    val setOnClickListener: ((View) -> Unit)? = null
)