package com.owulia.makekotlin.ui.activity

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity

class UserRegisterActivity : BaseActivity() {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_register

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
    }

}
