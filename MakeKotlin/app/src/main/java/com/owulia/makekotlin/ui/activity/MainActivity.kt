package com.owulia.makekotlin.ui.activity

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity

class MainActivity : BaseActivity() {

    override val mNavBarTitle: Int = R.string.text_error_tip

    override fun getContentViewResourceId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
    }

}
