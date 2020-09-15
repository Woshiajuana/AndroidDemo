package com.owulia.makekotlin.ui.activity

import com.owulia.makekotlin.R
import com.owulia.makekotlin.base.BaseActivity

class UserAccountActivity : BaseActivity() {

    override fun getContentViewResourceId(): Int = R.layout.activity_user_account

    override val isUseNavBar: Boolean = false

    override fun initView() {
        super.initView()
        render(RenderState.SUCCESS)
    }
}
