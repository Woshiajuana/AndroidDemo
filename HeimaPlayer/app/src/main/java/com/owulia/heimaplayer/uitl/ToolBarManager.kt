package com.owulia.heimaplayer.uitl

import android.widget.Toolbar
import com.owulia.heimaplayer.R

interface ToolBarManager {

    val toolbar: Toolbar

    fun initMainToolBar () {
        toolbar.apply {
            title = "黑马影音"
            inflateMenu(R.menu.main)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.setting -> {
                        // 跳转到设置页面
                    }
                }
                true
            }
        }
    }
}