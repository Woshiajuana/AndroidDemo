package com.owulia.makekotlin.ui.activity

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity

class UserForgetActivity : BaseActivity() {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_forget

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
    }

}
