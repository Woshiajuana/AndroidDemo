package com.owulia.heimaplayer.uitl

import android.widget.Toolbar
import com.owulia.heimaplayer.R

interface ToolBarManager {

    val toolbar: Toolbar

    fun initMainToolBar () {
        toolbar.title = "黑马影音"
        toolbar.inflateMenu(R.menu.main)
    }
}