package com.owulia.heimaplayer.ui.activity

import android.widget.Toolbar
import com.owulia.heimaplayer.R
import com.owulia.heimaplayer.base.BaseActivity
import com.owulia.heimaplayer.uitl.ToolBarManager
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), ToolBarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initData() {
        super.initData()
        initMainToolBar()
    }

}
